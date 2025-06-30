package es.uvigo.esei.proii.iu;

import es.uvigo.esei.proii.entidades.Universidad;

public class IlcUniversidad {

//    private Entrada entrada = new Entrada(); // CREO QUE ES FUNCIÓN DEL COSTRUCTOR
    private Entrada entrada;
    private Universidad universidad;
    private IlcGestionDocentesUniversidad gestionDocentesUniversidad;
    private IlcGestionEstudiantesUniversidad gestionEstudiantesUniversidad;

    public IlcUniversidad() {
        entrada = new Entrada();
        universidad = crearUniversidad();
        gestionDocentesUniversidad = new IlcGestionDocentesUniversidad(universidad, entrada);
        gestionEstudiantesUniversidad = new IlcGestionEstudiantesUniversidad(universidad, entrada);
    }

    /**
     * Realiza el reparto de la funcionalidad ler = lee, evalua, repite
     */
    public void ler() {

        int opcion = 0;
        do {
            try {
                opcion = menuPrincipal();

                switch (opcion) {
                    case 1:
                        gestionDocentesUniversidad.gestionDocentes();
                        break;
                    case 2:
                        gestionEstudiantesUniversidad.gestionEstudiantes();
                        break;
                    case 3:
//                    visualizarUniversidad();
                        System.out.println(universidad.toString());
                        break;
                }
            } catch (IllegalArgumentException 
                    | ArrayIndexOutOfBoundsException exc) {
                System.err.println("\nError. " + exc.getMessage()
                        + "\n");
            } catch (Exception exc) {
                System.err.println("\nError inesperado. " + exc.getMessage()
                        + "\n");
            }
        } while (opcion != 0);
    }

    /**
     * Crea un objeto Universidad leyendo del teclado sus datos
     *
     * @return Universidad Objeto Universidad
     */
    private Universidad crearUniversidad() {
        String nombre = entrada.leeCadena("Nombre universidad: ");
        int maxDocentes = entrada.leeEntero("Num. max. docentes: ");
        int maxEstudiantes = entrada.leeEntero("Num. max. estudiantes: ");

        return new Universidad(nombre, maxDocentes,
                maxEstudiantes);
    }

    /**
     * Visualiza los datos de la Universidad
     */
    private void visualizarUniversidad() {
        System.out.println("La universidad " + universidad.getNombre());
        gestionDocentesUniversidad.listarDocentes();
        gestionEstudiantesUniversidad.listarEstudiantes();
    }

    /**
     * Presenta un menu principal con las opciones, y permite seleccionar una.
     *
     * @return la opcion seleccionada, como entero
     */
    private int menuPrincipal() {
        int toret;

        System.out.println("En la Universidad existen: ");
        System.out.println(universidad.getNumDocentes() + " / "
                + universidad.getMaxDocentes() + " DOCENTES");
        System.out.println(universidad.getNumEstudiantes() + " / "
                + universidad.getMaxEstudiantes() + " ESTUDIANTES");
        do {
            System.out.println("\n\n\tGESTIÓN UNIVERSIDAD\n"
                    + "\n1. Gestión docentes "
                    + "\n2. Gestión estudiantes "
                    + "\n3. Listar datos universidad"
                    + "\n0. Salir\n");
            toret = entrada.leeEntero("Selecciona: ");
            if (toret < 0 || toret > 3) {
                System.out.println("Opción inválida: " + toret + "\n");
            }
            System.out.println();
        } while (toret < 0 || toret > 3);

        return toret;
    }

}
