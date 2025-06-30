package es.uvigo.esei.proii.iu;

import es.uvigo.esei.proii.entidades.Docente;
import es.uvigo.esei.proii.entidades.Estudiante;
import es.uvigo.esei.proii.entidades.Fecha;
import es.uvigo.esei.proii.entidades.Funcionario;
import es.uvigo.esei.proii.entidades.Hora;
import es.uvigo.esei.proii.entidades.Tutoria;
import es.uvigo.esei.proii.entidades.Universidad;
import es.uvigo.esei.proii.excepciones.ExisteDocenteNrpException;
import es.uvigo.esei.proii.excepciones.ExisteEstudianteException;
import es.uvigo.esei.proii.excepciones.NoExisteDocenteException;
import es.uvigo.esei.proii.excepciones.NoExisteEstudianteException;

/**
 *
 * @author Nanny
 */
public class IlcGestionTutoriasUniversidad {

    private Universidad universidad;
    private Entrada entrada;
    private IlcGestionFechaHora gestionFechaHora;

    public IlcGestionTutoriasUniversidad(Universidad universidad,
            Entrada entrada) {
        this.universidad = universidad;
        this.entrada = entrada;
        gestionFechaHora = new IlcGestionFechaHora(entrada);
    }

    /**
     * Muestra las distintas opciones para gestionar las tutorias y lanza los
     * métodos que realiza cada operación
     */
    public void gestionTutorias() {
        int opcion = 0;

        do {
            opcion = menuGestionTutorias();
            switch (opcion) {
                case 1:
                    insertaTutoria();
                    break;
                case 2:
                    modificaTutoria();
                    break;
                case 3:
                    eliminaTutoria();
                    break;
                case 4:
                    listarTutorias();
                    break;
            }
        } while (opcion != 0);
    }

    /**
     * Presenta un menu con las opciones para gestionar tutorias, y permite
     * seleccionar una.
     *
     * @return la opcion seleccionada, como entero
     */
    private int menuGestionTutorias() {
        int toret;

        do {
            System.out.println("\nGESTION TUTORIAS");
            System.out.println(
                    "\n1. Insertar tutoria\n"
                    + "2. Modificar tutoria\n"
                    + "3. Eliminar tutoria\n"
                    + "4. Listar tutorias\n"
                    + "0. Volver al menú principal\n");
            toret = entrada.leeEntero("Selecciona: ");
        } while (toret < 0
                || toret > 4);

        System.out.println();
        return toret;
    }

    /**
     * Crea un nuevo tutoria y lo inserta en la universidad
     *
     */
    private void insertaTutoria() {
        System.out.println("\n-------------");
        System.out.println("\nAlta tutoria");

        if (universidad.getNumDocentes() == 0) {
            throw new NoExisteDocenteException("No existen docentes"
                    + " en la universidad.");
        } else if (universidad.getNumEstudiantes() == 0) {
            throw new NoExisteEstudianteException("No existen estudiantes"
                    + " en la universidad.");
        }

        Tutoria t = leeTutoria();
        universidad.insertaTutoria(t);

    }

    /**
     * Lee del teclado los datos de una tutoria.
     *
     * @return El objeto Tutoria creado
     */
    private Tutoria leeTutoria()
            throws NoExisteDocenteException, NoExisteEstudianteException {
        Fecha fecha = new Fecha();
        Hora hora = new Hora();
        Docente d = new Funcionario(fecha, 0, "", "", 0);
        Estudiante e = new Estudiante("", 0, "");
        Tutoria t = new Tutoria(d, e, fecha, hora);

        System.out.println("\nIntroduce los datos de la nueva tutoria:");

        modificaTutoria(t);

        return t;
    }

    private void insertarTutoria(Tutoria t) {

        universidad.insertaTutoria(t);
    }

    /**
     * Borra un tutoria por su posicion en la universidad.
     *
     */
    private void eliminaTutoria() {
        if (universidad.getNumTutorias() > 0) {
            universidad.eliminaTutoria(leePosTutoria());
        } else {
            System.out.println("La universidad no contiene tutorias.");
        }
    }

    /**
     * Modifica un tutoria existente.
     *
     */
    private void modificaTutoria() {
        System.out.println("\n---------------------");
        System.out.println("\nModificación tutoria");

        if (universidad.getNumTutorias() > 0) {
            modificaTutoria(universidad.getTutoria(leePosTutoria()));
        } else {
            System.out.println("La universidad no contiene tutorias.");
        }
    }

    /**
     * Modifica/lee los datos de una tutoría.
     *
     * @param t La tutoría a modificar.
     */
    private void modificaTutoria(Tutoria t) {

        String info;
        boolean vacio = false;
        int pos = -1;

        // Numero de registro personal del docente. Debe existir
        System.out.print("\tNumero de registro de personal del docente ");
        if (t.getProfesor().getNrp().length() > 0) {
            System.out.print(" [" + t.getProfesor().getNrp() + "]");
            vacio = true;
        }
        info = entrada.leeCadena(": ", vacio);
        if (info.length() > 0) {
            int posDocente = universidad.esUnicoNrp(info);
            if (posDocente == universidad.getNumDocentes()) {
                throw new ExisteDocenteNrpException("");
            }

            t.setProfesor(universidad.getDocente(posDocente));

        }

        // Numero de matricula del estudiante. Debe existir
        System.out.print("\tNumero de matricula del estudiante ");
        if (t.getEstudiante().getNumeroMatricula().length() > 0) {
            System.out.print(" [" + t.getEstudiante().getNumeroMatricula() + "]");
            vacio = true;
        }
        info = entrada.leeCadena(": ", vacio);
        if (info.length() > 0) {
            int posEstudiante = universidad.comprobarMatricula(info);
            if ( posEstudiante == universidad.getNumEstudiantes()) {
                throw new ExisteEstudianteException("");
            }

            t.setEstudiante(universidad.getEstudiante(posEstudiante));
        }

        // Fecha tutoria
        t.setFechaTutoria(gestionFechaHora.modificaFecha("\tFecha de la tutoría ",
                t.getFechaTutoria()));

        // Hora tutoria
        t.setHoraTutoria(gestionFechaHora.modificaHora("\tHora de la tutoría ",
                t.getHoraTutoria()));
    }

    /**
     * Lee del teclado la posición de una tutoria en la colección
     *
     * @return la posición de la tutoria, como entero.
     */
    private int leePosTutoria() {
        return entrada.leeEntero("Introduzca posición de la tutoria",
                1, universidad.getNumTutorias()) - 1;
    }

    /**
     * Visualiza las tutorias almacenados en la universidad por la salida std.
     *
     */
    void listarTutorias() {
        final int numTutorias = universidad.getNumTutorias();

        if (numTutorias > 0) {
            System.out.print("\nTutorias:\n");
            for (int i = 0; i < numTutorias; i++) {
                System.out.print((i + 1) + ". ");
                System.out.println(universidad.getTutoria(i).toString());
                System.out.println("\n");
            }
        } else {
            System.out.println("No hay tutorias.");
        }

    }

}
