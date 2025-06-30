package es.uvigo.esei.proii.iu;

import es.uvigo.esei.proii.entidades.Docente;
import es.uvigo.esei.proii.entidades.Fecha;
import es.uvigo.esei.proii.entidades.Funcionario;
import es.uvigo.esei.proii.entidades.Laboral;
import es.uvigo.esei.proii.entidades.Persona;
import es.uvigo.esei.proii.entidades.Universidad;

public class IlcGestionDocentesUniversidad {

    private Universidad universidad;
    private Entrada entrada;
    private IlcGestionPersona gestionPersona;
    private IlcGestionFechaHora gestionFechaHora;

    public IlcGestionDocentesUniversidad(Universidad universidad,
            Entrada entrada) {
        this.universidad = universidad;
        this.entrada = entrada;
        gestionPersona = new IlcGestionPersona(entrada);
        gestionFechaHora = new IlcGestionFechaHora(entrada);
    }

    /**
     * Muestra las distintas opciones para gestionar los docentes y lanza los
     * métodos que realiza cada operación
     */
    public void gestionDocentes() {
        int opcion = 0;
        TipoDocente tipo;

        do {
            opcion = menuGestionDocentes();
            switch (opcion) {
                case 1:
                    insertaDocente();
                    break;
                case 2:
                    modificaDocente();
                    break;
                case 3:
                    eliminaDocente();
                    break;
                case 4:
                    listarDocentes();
                    break;
                case 5:
                    tipo = leeTipoDocente("\nIntroduce el "
                            + "tipo de los docentes a listar.");
//                    System.out.println(listarDocentesDedicacion(tipo));
                    break;
            }
        } while (opcion != 0);
    }

    /**
     * Presenta un menu con las opciones para gestionar docentes, y permite
     * seleccionar una.
     *
     * @return la opcion seleccionada, como entero
     */
    private int menuGestionDocentes() {
        int toret;

        do {
            System.out.println("\nGESTION DOCENTES");
            System.out.println("""
                               
                               1. Insertar docente
                               2. Modificar docente
                               3. Eliminar docente
                               4. Listar docentes
                               5. Listar docentes por tipo
                               0. Volver al men\u00fa principal
                               """);
            toret = entrada.leeEntero("Selecciona: ");
        } while (toret < 0
                || toret > 5);

        System.out.println();
        return toret;
    }

    /**
     * Crea un nuevo docente y lo inserta en la universidad
     *
     */
    private void insertaDocente() {
        System.out.println("\n-------------");
        System.out.println("\nAlta docente");

        Docente p = leeDocente();
        universidad.insertaDocente(p);
    }

    /**
     * Lee del teclado los datos de un docente.
     *
     * @return El objeto Docente creado
     */
    private Docente leeDocente() {
        Docente d = null;
        Fecha fecha = new Fecha();

        System.out.println("\nIntroduce los datos del nuevo docente:");

        TipoDocente tipo = leeTipoDocente("\tIntroduce tipo de docente: ");

        switch (tipo) {
            case FUNCIONARIO:
                d = new Funcionario(fecha, 0, "", "", 0);
                gestionPersona.modificaPersona(d);
                modificaDocenteBasica(d);
                modificaFuncionario((Funcionario) d);
                break;
            case LABORAL:
                d = new Laboral(fecha, fecha, 0, "", "", 0);
                gestionPersona.modificaPersona(d);
                modificaDocenteBasica(d);
                modificaLaboral((Laboral) d);
                break;
        }

        return d;
    }

    /**
     * Lee el tipo del docente como un enumerado
     *
     * @param msg Mensaje que se muestra al usuario
     * @return El enumerado que indica el tipo del docente
     */
    private TipoDocente leeTipoDocente(String msg) {
        int opcion;

        do {
            for (TipoDocente d : TipoDocente.values()) {
                System.out.print("\tIntroduce " + (d.ordinal() + 1)
                        + " para " + d.toString());
            }
            opcion = entrada.leeEntero(msg);
        } while ((opcion < 1) || (opcion > TipoDocente.values().length));

        return TipoDocente.values()[opcion - 1];
    }

    /**
     * Borra un docente por su posicion en la universidad.
     *
     */
    private void eliminaDocente() {
        if (universidad.getNumDocentes() > 0) {
            universidad.eliminaDocente(leePosDocente());
        } else {
            System.out.println("La universidad no contiene docentes.");
        }
    }

    /**
     * Modifica un docente existente.
     *
     */
    private void modificaDocente() {
        System.out.println("\n---------------------");
        System.out.println("\nModificación docente");

        if (universidad.getNumDocentes() > 0) {
            modificaDocente(universidad.getDocente(leePosDocente()));
        } else {
            System.out.println("La universidad no contiene docentes.");
        }
    }

    private void modificaDocente(Docente d) {
        boolean vacio = false;
        String info;

        System.out.println("\nModificando los datos del siguiente docente:");
        System.out.println(d);
        System.out.println();

        gestionPersona.modificaPersona(d);
        modificaDocenteBasica(d);

        if (d instanceof Funcionario) {
            modificaFuncionario((Funcionario) d);
        } else {
            modificaLaboral((Laboral) d);
        }
    }

    private void modificaDocenteBasica(Docente p) {
        String info;

        // N.R.P.
        System.out.print("\tN.R.P. ");
        if (p.getNrp().length() > 0) {
            System.out.print("[" + p.getNrp() + "]");
        }
        info = entrada.leeCadena(": ", p.getNrp().length() > 0);
        if (info.length() > 0) {
            p.setNrp(info);
        }

        // Despacho
        System.out.print("\tDespacho ");
        if (p.getDespacho() != 0) {
            System.out.print("[" + p.getDespacho() + "]");
        }
        info = entrada.leeCadena(": ", p.getDespacho() != 0);

        if (info.length() > 0) {
            p.setDespacho(Integer.parseInt(info));
        }

    }

    private void modificaFuncionario(Funcionario f) {
        Fecha tomaPosesion = new Fecha(0, 0, 0);

        f.setFechaPosesion(gestionFechaHora.modificaFecha("Fecha de toma "
                + "de posesión: ", tomaPosesion));
    }

    private void modificaLaboral(Laboral l) {
        Fecha fecha = new Fecha(0, 0, 0);
       
        l.setFechaInicio(gestionFechaHora.modificaFecha("Fecha inico "
                + "de contrato: ", fecha));
        l.setFechaFin(gestionFechaHora.modificaFecha("Fecha de fin "
                + "de contrato: ", fecha));
    }

    /**
     * Lee del teclado la posición de un docente en la colección
     *
     * @return la posición del docente, como entero.
     */
    private int leePosDocente() {
        return entrada.leeEntero("Introduzca posición del docente ", 1, universidad.getNumDocentes()) - 1;
    }

    /**
     * Visualiza los docentes almacenados en la universidad por la salida std.
     *
     */
    void listarDocentes() {
        final int numDocentes = universidad.getNumDocentes();

        if (numDocentes > 0) {
            System.out.print("\nDocentes:\n");
            for (int i = 0; i < numDocentes; i++) {
                System.out.print((i + 1) + ". ");
                System.out.println(universidad.getDocente(i).toString());
                System.out.println("\n");
            }
        } else {
            System.out.println("No hay docentes.");
        }

    }

}
