package es.uvigo.esei.proii.iu;

import es.uvigo.esei.proii.entidades.Universidad;
import java.io.IOException;

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
        guardarXML(coleccion);
    }

    private void guardarXML(Universidad coleccion) {
        try {
            coleccion.toXML(RUTA_DOCENTES, RUTA_ESTUDIANTES, RUTA_TUTORIAS);
        } catch (IOException ex) {
            System.err.println("Se ha producido un error al guardar los datos");
            System.err.println("Error: " + ex.getMessage());
        }
    }

    private Universidad crearUniversidadXML() {
        String nombre = leeCadena("Nombre universidad: ");
        Universidad uni = null;

        try {
            uni = new Universidad(nombre, RUTA_DOCENTES,
                    RUTA_ESTUDIANTES, RUTA_TUTORIAS);
            System.out.println("Se ha leido los datos de "+nombre+" a partir de ficheros");
        } catch (ParsingException ex) {
            System.err.println("Se ha producido un error en el parsing, abortando"
                    + "la lectura de ficheros");
            System.err.println("Error: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Se ha producido un error de I/O, abortando"
                    + "la lectura de ficheros");
            System.err.println("Error: " + ex.getMessage());
        }
        if (uni == null) {
            uni = crearUniversidad(nombre);
        }
        
        return uni;
    }

    private Universidad crearUniversidad(String nombre) {
        System.out.println("Creando la universidad " + nombre + " desde 0");
        int maxDocentes = entrada.leeEntero("Num. max. docentes: ");
        int maxEstudiantes = entrada.leeEntero("Num. max. estudiantes: ");
        int maxTutorias = entrada.leeEntero("Num. max. tutorias: ");
        return new Universidad(nombre, maxDocentes,
                maxEstudiantes, maxTutorias);
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
