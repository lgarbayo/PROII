package es.uvigo.esei.proii.iu;

import es.uvigo.esei.proii.entidades.Universidad;

public class IlcUniversidad {

    private Entrada entrada;
    private Universidad universidad;
    private IlcGestionDocentesUniversidad gestionDocentesUniversidad;
    private IlcGestionEstudiantesUniversidad gestionEstudiantesUniversidad;
    private IlcGestionTutoriasUniversidad gestionTutoriasUniversidad;
    
    public IlcUniversidad() {
        entrada = new Entrada();
        universidad = crearUniversidad();
        gestionDocentesUniversidad = new IlcGestionDocentesUniversidad(universidad, entrada);
        gestionEstudiantesUniversidad = new IlcGestionEstudiantesUniversidad(universidad, entrada);
        gestionTutoriasUniversidad = new IlcGestionTutoriasUniversidad (universidad, entrada);
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
                        gestionTutoriasUniversidad.gestionTutorias();
                        break;
                    case 4:
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
        int maxTutorias = entrada.leeEntero("Num. max. tutorias: ");

        return new Universidad(nombre, maxDocentes,
                maxEstudiantes, maxTutorias);
    }

    /**
     * Visualiza los datos de la Universidad
     */
    private void visualizarUniversidad() {
        System.out.println("La universidad " + universidad.getNombre());
        gestionDocentesUniversidad.listarDocentes();
        gestionEstudiantesUniversidad.listarEstudiantes();
        gestionTutoriasUniversidad.listarTutorias();
    }
            
    /**
     * Presenta un menu principal con las opciones, y permite seleccionar una.
     *
     * @return la opcion seleccionada, como entero
     */
    private int menuPrincipal() {
        int toret;

        System.out.println("\nEn la Universidad existen: ");
        System.out.println(universidad.getNumDocentes() + " DOCENTES");
        System.out.println(universidad.getNumEstudiantes() + " ESTUDIANTES");
        System.out.println(universidad.getNumTutorias() + " TUTORIAS");
        do {
            System.out.println("\n\n\tGESTIÓN UNIVERSIDAD\n"
                    + "\n1. Gestión docentes "
                    + "\n2. Gestión estudiantes "
                    + "\n3. Gestión tutorías"
                    + "\n4. Listar datos universidad"
                    + "\n0. Salir\n");
            toret = entrada.leeEntero("Selecciona: ");
            if (toret < 0 || toret > 4) {
                System.out.println("Opción inválida: " + toret + "\n");
            }
            System.out.println();
        } while (toret < 0 || toret > 4);

        return toret;
    }

}
