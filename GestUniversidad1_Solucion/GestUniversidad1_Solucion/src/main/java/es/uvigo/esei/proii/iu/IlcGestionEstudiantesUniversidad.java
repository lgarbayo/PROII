package es.uvigo.esei.proii.iu;

import es.uvigo.esei.proii.entidades.Estudiante;
import es.uvigo.esei.proii.entidades.Universidad;

public class IlcGestionEstudiantesUniversidad {

    private Universidad universidad;
    private Entrada entrada;

    public IlcGestionEstudiantesUniversidad(Universidad universidad, 
            Entrada entrada) {
        this.universidad = universidad;
        this.entrada = entrada;
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
                case 5:
                    System.out.println(listarEstudiantesErasmus());
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
            System.out.println("GESTION ESTUDIANTES");
            System.out.println(
                    "\n1. Insertar estudiante\n"
                    + "2. Modificar estudiante\n"
                    + "3. Eliminar estudiante\n"
                    + "4. Listar estudiantes\n"
                    + "5. Listar estudiantes erasmus\n"
                    + "0. Volver al menú principal\n");
            toret = entrada.leeEntero("Selecciona: ");
        } while (toret < 0
                || toret > 5);

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
        System.out.println("\nIntroduce los datos del nuevo estudiante:");

        String dni = entrada.leeCadena("\tD.N.I.: ");
        String nombre = entrada.leeCadena("\tNombre: ");
        
        String tipoEstudiante = leeTipoEstudiante("\tEl estudiante es"
                + " de erasmus (S/N): ");
        boolean esErasmus = tipoEstudiante.equals("S");


        return new Estudiante(dni, nombre, esErasmus);
    }

    /**
     * Pregunta si el estudiante es de erasmus
     *
     * @param msg Mensaje que se muestra al usuario
     * @return La cadena que indica si el estudiante es de erasmus
     */
    private String leeTipoEstudiante(String msg) {
        String esErasmus = "";
        do {
            esErasmus = entrada.leeCadena(msg).toUpperCase();

            if (!esErasmus.equals("S")
                    && !esErasmus.equals("N")) {
                System.err.println("Contesta S: si o N: no. "
                        + "Por favor, introdúcela de nuevo.");
            }
        } while (!esErasmus.equals("S")
                && !esErasmus.equals("N"));

        return esErasmus;
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

        // D.N.I.
        System.out.print("D.N.I. ");
        if (e.getDni().length() > 0) {
            System.out.print("[" + e.getDni() + "]");            
        }
        info = entrada.leeCadena(": ", e.getDni().length() > 0);
        if (info.length() > 0) {
            e.setDni(info);
        }

        // Nombre
        System.out.print("Nombre ");
        if (e.getNombre().length() > 0) {
            System.out.print("[" + e.getNombre() + "]");            
        }
        info = entrada.leeCadena(": ", e.getNombre().length() > 0);
        if (info.length() > 0) {
            e.setNombre(info);
        }

        // Es Erasmus. ES OBLIGATORIO MODIFICAR (INTRODUCIR) SI EL 
        // ESTUDIANTE E O NO ERAMUS
        String erasmusActual = e.isEsErasmus()? "Si" : "No";
        String esErasmus = leeTipoEstudiante("\tEl estudiante es"
                + " Erasmus (S/N) ["
                + erasmusActual + "]: ");
        e.setEsErasmus(esErasmus.equals("S"));
    }

    /**
     * Lee del teclado la posición de un estudiante en la colección
     *     
     * @return la posición del estudiante, como entero.
     */
    private int leePosEstudiante() {
        return entrada.leeEntero("Introduzca posición del estudiante", 
                1, universidad.getNumEstudiantes()) - 1;
    }

    /**
     * Visualiza los estudiantes almacenados en la universidad por la salida std.
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
    
    private String listarEstudiantesErasmus(){
        int numEstudiantes = universidad.getNumEstudiantes();
        StringBuilder toret = new StringBuilder();
        StringBuilder toretDef = new StringBuilder();
        
        for (int i = 0; i < numEstudiantes; i++) {
            if (universidad.getEstudiante(i).isEsErasmus()){
                toret.append(universidad.getEstudiante(i).toString());
            }
        }
        if (toret.isEmpty()){
            toretDef.append("En la universidad no existen estudiantes "
                    + "de erasmus");
        }
        else{
            toretDef.append("Los estudiantes de Erasmus son")
                    .append(toret);
        }
        
        return toretDef.toString();
    }

}
