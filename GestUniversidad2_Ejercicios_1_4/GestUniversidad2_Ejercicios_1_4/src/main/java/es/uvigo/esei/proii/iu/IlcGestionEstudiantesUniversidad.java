package es.uvigo.esei.proii.iu;

import es.uvigo.esei.proii.entidades.Estudiante;
import es.uvigo.esei.proii.entidades.Universidad;

public class IlcGestionEstudiantesUniversidad {

    private Universidad universidad;
    private Entrada entrada;
    private IlcGestionPersona gestionPersona;
    private IlcGestionFechaHora gestionFechaHora;

    public IlcGestionEstudiantesUniversidad(Universidad universidad,
            Entrada entrada) {
        this.universidad = universidad;
        this.entrada = entrada;
        gestionPersona = new IlcGestionPersona(entrada);
        gestionFechaHora = new IlcGestionFechaHora(entrada);
    }

    /**
     * INTERFAZ/LÓGICA/CONTROL ESTUDIANTES *
     */
    /**
     * Muestra las distintas opciones para gestionar los estudiantes y lanza los
     * métodos que realiza cada operación
     */
    void gestionEstudiantes() {
        int opcion = 0;

        do {
            opcion = menuGestionEstudiantes();
            switch (opcion) {
                case 1:
                    insertaEstudiante();
                    break;
                case 2:
                    modificaEstudiante();
                    break;
                case 3:
                    eliminaEstudiante();
                    break;
                case 4:
                    listarEstudiantes();
                    break;
            }
        } while (opcion != 0);
    }

    /**
     * Presenta un menu con las opciones para gestionar estudiantes, y permite
     * seleccionar una.
     *
     * @return la opcion seleccionada, como entero
     */
    private int menuGestionEstudiantes() {
        int toret;

        do {
            System.out.println("\nGESTION ESTUDIANTES");
            System.out.println(
                    "\n1. Insertar estudiante\n"
                    + "2. Modificar estudiante\n"
                    + "3. Eliminar estudiante\n"
                    + "4. Listar estudiantes\n"
                    + "0. Volver al menú principal\n");
            toret = entrada.leeEntero("Selecciona: ");
        } while (toret < 0
                || toret > 4);

        System.out.println();
        return toret;
    }

    /**
     * Crea un nuevo estudiante y lo inserta en la universidad
     *
     */
    private void insertaEstudiante() {
        System.out.println("\n-------------");
        System.out.println("\nAlta estudiante");

        Estudiante e = leeEstudiante();
        universidad.insertaEstudiante(e);
    }

    /**
     * Lee del teclado los datos de un estudiante.
     *
     * @return El objeto Estudiante creado
     */
    private Estudiante leeEstudiante() {
        Estudiante e = new Estudiante("", 0, "");
        System.out.println("\nIntroduce los datos del nuevo estudiante:");

        gestionPersona.modificaPersona(e);
        modificaEstudianteBasica(e);

        return e;
    }

    /**
     * Borra un estudiante por su posicion en la universidad.
     *
     */
    private void eliminaEstudiante() {
        if (universidad.getNumEstudiantes() > 0) {
            universidad.eliminaEstudiante(leePosEstudiante());
        } else {
            System.out.println("La universidad no contiene estudiantes.");
        }
    }

    /**
     * Modifica un estudiante existente.
     *
     */
    private void modificaEstudiante() {
        System.out.println("\n---------------------");
        System.out.println("\nModificación estudiante");

        if (universidad.getNumEstudiantes() > 0) {
            modificaEstudiante(universidad.getEstudiante(leePosEstudiante()));
        } else {
            System.out.println("La universidad no contiene estudiantes.");
        }
    }

    private void modificaEstudiante(Estudiante e) {
        String info;

        System.out.println("\nModificando los datos del siguiente estudiante:");
        System.out.println(e);
        System.out.println();

        gestionPersona.modificaPersona(e);
        modificaEstudianteBasica(e);

    }

    private void modificaEstudianteBasica(Estudiante e) {
        String info;

        // Numero matricula
        System.out.print("\tNumero matricula ");
        if (e.getNumeroMatricula().length() > 0) {
            System.out.print("[" + e.getNumeroMatricula() + "]");
        }
        info = entrada.leeCadena(": ", e.getNumeroMatricula().length() > 0);
        if (info.length() > 0) {
            e.setNumeroMatricula(info);
        }
    }

    /**
     * Lee del teclado la posición de un estudiante en la colección
     *
     * @return la posición del estudiante, como entero.
     */
    private int leePosEstudiante() {
        return entrada.leeEntero("Introduzca posición del estudiante ",
                1, universidad.getNumEstudiantes()) - 1;
    }

    /**
     * Visualiza los estudiantes almacenados en la universidad por la salida
     * std.
     *
     */
    void listarEstudiantes() {
        final int numEstudiantes = universidad.getNumEstudiantes();

        if (numEstudiantes > 0) {
            System.out.print("\nEstudiantes:\n");
            for (int i = 0; i < numEstudiantes; i++) {
                System.out.print((i + 1) + ". ");
                System.out.println(universidad.getEstudiante(i).toString());
                System.out.println("\n");
            }
        } else {
            System.out.println("No hay estudiantes.");
        }

    }

}
