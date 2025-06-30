package es.uvigo.esei.proii.ui;

import es.uvigo.esei.proii.core.*;
import static es.uvigo.esei.proii.ui.Entrada.*;
import java.io.IOException;
import java.util.ArrayList;
import nu.xom.ParsingException;

/**
 * Interfaz de línea de comandos
 */
public class Ilc {

    private static final String RUTA_DOCENTES = "docentes.xml";
    private static final String RUTA_TUTORIAS = "tutorias.xml";
    private static final String RUTA_ESTUDIANTES = "estudiantes.xml";

    /**
     * Realiza el reparto de la funcionalidad ler = lee, evalua, repite
     */
    public void ler() {
        int opcion;

        Universidad coleccion;

        coleccion = crearUniversidadXML();

        do {
            opcion = menuPrincipal(coleccion);
            switch (opcion) {
                case 1:
                    gestionDocentes(coleccion);
                    break;
                case 2:
                    gestionEstudiantes(coleccion);
                    break;
                case 3:
                    gestionTutorias(coleccion);
                    break;
                case 4:
                    System.out.println(coleccion.toString());
                    break;
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
        int maxDocentes = leeEntero("Num. max. docentes: ");
        int maxEstudiantes = leeEntero("Num. max. estudiantes: ");
        int maxTutorias = leeEntero("Num. max. tutorias: ");
        return new Universidad(nombre, maxDocentes,
                maxEstudiantes, maxTutorias);
    }

    /**
     * Presenta un menu principal con las opciones, y permite seleccionar una.
     *
     * @return la opcion seleccionada, como entero
     */
    private int menuPrincipal(Universidad coleccion) {
        int toret;

        do {
            System.out.println("\nNumero de docentes: "
                    + coleccion.getNumDocentes());
            System.out.println("\nNumero de estudiantes: "
                    + coleccion.getNumEstudiantes());
            System.out.println("\nNumero de tutorias: "
                    + coleccion.getNumTutorias());

            System.out.println("\n\tGESTIÓN UNIVERSIDADA\n"
                    + "\n1. Gestión docentes "
                    + "\n2. Gestión estudiantes "
                    + "\n3. Gestión tutorias "
                    + "\n4. Listar datos universidad "
                    + "\n0. Salir\n");
            toret = leeEntero("Selecciona: ");
            if (toret < 0 || toret > 4) {
                System.out.println("Opción inválida: " + toret + "\n");
            }
            System.out.println();
        } while (toret < 0 || toret > 4);

        return toret;
    }

    /**
     *   * INTERFAZ/LÓGICA/CONTROL DOCENTES *
     */
    /**
     * Muestra las distintas opciones para gestionar los docentes y lanza los
     * métodos que realiza cada operación
     *
     * @param coleccion La Universidad sobre la que actua
     */
    private void gestionDocentes(Universidad coleccion) {
        int opcion;

        do {
            opcion = menuGestionDocentes();
            switch (opcion) {
                case 1:
                    insertaDocente(coleccion);
                    break;
                case 2:
                    modificaDocente(coleccion);
                    break;
                case 3:
                    eliminaDocente(coleccion);
                    break;
                case 4:
                    listarDocentes(coleccion);
                    break;
                case 5:
                    listarDocentesPorTipo(coleccion);
                    break;
            }
        } while (opcion != 0);
    }

    /**
     * Presenta un menu con las opciones para gestionar docentes, y permite
     * seleccionar una.
     *
     * @param coleccion La Universidad sobre la que se realizan las operaciones
     * @return la opcion seleccionada, como entero
     */
    private int menuGestionDocentes() {
        int toret;

        do {
            System.out.println("GESTION PROFESORES");
            System.out.println(
                    "\n1. Inserta un nuevo docente\n"
                    + "2. Modifica un docente\n"
                    + "3. Elimina un docente\n"
                    + "4. Listar docentes\n"
                    + "5. Listar docentes por tipo\n"
                    + "0. Volver al menú principal\n");
            toret = leeEntero("Selecciona: ");
        } while (toret < 0 || toret > 5);
        System.out.println();
        return toret;
    }

    /**
     * Crea un nuevo docente y lo inserta en la coleccion
     *
     * @param coleccion La coleccion en la que se inserta el docente.
     */
    private void insertaDocente(Universidad coleccion) {
        System.out.println("\n-------------");
        System.out.println("\nAlta docente");

        Docente p = leeDocente();
        try {
            coleccion.insertaDocente(p);
        } catch (NRPInvalidoException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    /**
     * Lee del teclado los datos de un docente.
     *
     * @return El objeto Docente creado
     */
    private Docente leeDocente() {

        System.out.println("\nIntroduce los datos del nuevo docente:");
        //Lectura de datos de persona
        int dni = leeEntero("\tD.N.I.: ");
        String nombre = leeCadena("\tNombre: ", false);

        //Lectura de datos de Docente
        int despacho = leeEntero("\tDespacho: ");
        String nrp = leeCadena("\tNRP: ", false);

        Docente d = null; //Para que compile
        int opt = leerClaseDocente("Seleccione el tipo de Docente a leer: ");
        switch (opt) {
            case 0:  //Funcionario
                d = leerFuncionario(nombre, dni, despacho, nrp);
                break;
            case 1: //Laboral
                d = leerLaboral(nombre, dni, despacho, nrp);
                break;
            default:
                System.out.println("OPCION NO IMPLEMENTADA");
        }
        return d;
    }

    /**
     * Lee los datos propios de un Funcionario
     *
     * @param nombre el nombre del Funcionario
     * @param dni el dni del Funcionario
     * @param despacho el despacho del Funcionario
     * @param nrp el nrp del Funcionario
     * @return El Funcionario
     */
    private Funcionario leerFuncionario(String nombre, int dni, int despacho, String nrp) {
        Fecha fecha = leerFecha("\tFecha: ");
        return new Funcionario(fecha, despacho, nrp, nombre, dni);
    }

    /**
     * Lee los datos propios de un Laboral
     *
     * @param nombre el nombre del Laboral
     * @param dni el dni del Laboral
     * @param despacho el despacho del Laboral
     * @param nrp el nrp del Laboral
     * @return El Laboral
     */
    private Laboral leerLaboral(String nombre, int dni, int despacho, String nrp) {
        Laboral l = null;
        boolean flag = true;
        do {
            try {
                Fecha fechaInicio = leerFecha("\tFecha Inicio: ");
                Fecha fechaFin = leerFecha("\tFecha Fin: ");
                l = new Laboral(fechaInicio, fechaFin, despacho, nrp, nombre, dni);
                flag = false;
            } catch (FechaException e) {
                System.err.println("Fecha Invalida: " + e.getMessage());
            }
        } while (flag);

        return l;
    }

    /**
     * Lee la subClase del Docente
     *
     * @param msg el mensaje a mostrar
     * @return La subClase, como entero (0-Funcionario / 1-Laboral)
     */
    private int leerClaseDocente(String msg) {
        StringBuilder sb = new StringBuilder();
        sb.append("Tipos de Docentes posibles:");
        enum clasesDocente {
            Funcionario, Laboral
        };
        clasesDocente[] tipos = clasesDocente.values();
        int size = tipos.length;
        for (int i = 0; i < size; i++) {
            sb.append(i + 1).append(". ").append(tipos[i]).append("\n");
        }
        int opt;
        do {
            System.out.println(sb.toString());
            opt = leeEntero(msg) - 1;
        } while (opt < 0 || opt >= size);

        return opt;
    }

    /**
     * Borra un docente por su posicion en la colección.
     *
     * @param coleccion La coleccion en la que se elimina el docente
     */
    private void eliminaDocente(Universidad coleccion) {
        if (coleccion.getNumDocentes() > 0) {
            try {
                coleccion.eliminaDocente(leePosDocente(coleccion));
            } catch (NoEliminableException ex) {
                System.err.println("Docente no Eliminable: " + ex.getMessage());
            }
        } else {
            System.out.println("La coleccion no contiene docentes.");
        }
    }

    /**
     * Modifica un docente existente.
     *
     * @param coleccion La coleccion de la cual modificar un docente.
     */
    private void modificaDocente(Universidad coleccion) {

        System.out.println("\n---------------------");
        System.out.println("\nModificación docente");

        if (coleccion.getNumDocentes() > 0) {
            this.modificaDocente(coleccion.getDocente(this.leePosDocente(coleccion)), coleccion);
        } else {
            System.out.println("La coleccion no contiene docentes.");
        }
    }

    private void modificaDocente(Docente d, Universidad coleccion) {

        System.out.println("\nModificando los datos del siguiente docente:");
        System.out.println(d);
        System.out.println();

        modificarDocenteBasico(d, coleccion);
        StringBuilder sb = new StringBuilder("Clase actual: ");
        if (d instanceof Funcionario) {
            sb.append("Funcionario");
        }
        if (d instanceof Laboral) {
            sb.append("Laboral ");
        }
        sb.append("\n Cambiar clase? ");
        int opt = leerClaseDocente(sb.toString());

        switch (opt) {
            case 0: //Funcionario
                if (d instanceof Funcionario) {
                    modificarFuncionario((Funcionario) d);
                } else {
                    System.out.println("Dame los datos del nuevo Funcionario: ");
                    Funcionario funcionario = leerFuncionario(d.getNombre(), d.getDni(), d.getDespacho(), d.getNRP());
                    //Borrar el actual e insrtar el nuevo
                    int pos;
                    ArrayList<Tutoria> tutorias = new ArrayList<>();
                    do {
                        pos = coleccion.buscarTutoria(d);
                        if (pos != -1) {
                            tutorias.add(coleccion.getTutoria(pos));
                            coleccion.eliminaTutoria(pos);
                        }
                    } while (pos != -1);
                    coleccion.eliminaDocente(coleccion.buscarDocente(d.getNRP()));
                    coleccion.insertaDocente(funcionario);
                    for (Tutoria tutoria : tutorias) {
                        tutoria.setProfesor(funcionario);
                        coleccion.insertaTutoria(tutoria);
                    }
                }
                break;
            case 1: //Laboral
                if (d instanceof Laboral) {
                    modificarLaboral((Laboral) d);
                } else {
                    System.out.println("Dame los datos del nuevo Laboral: ");
                    Laboral laboral = leerLaboral(d.getNombre(), d.getDni(), d.getDespacho(), d.getNRP());
                    //Borrar el actual e insrtar el nuevo  
                    int pos;
                    //LIMPIA LAS TUTORIAS PARA PODER BORRAR Y LUEGO LAS CREA CON EL MISMO DOCENTE
                    ArrayList<Tutoria> tutorias = new ArrayList<>();
                    do {
                        pos = coleccion.buscarTutoria(d);
                        if (pos != -1) {
                            tutorias.add(coleccion.getTutoria(pos));
                            coleccion.eliminaTutoria(pos);
                        }
                    } while (pos != -1);
                    coleccion.eliminaDocente(coleccion.buscarDocente(d.getNRP()));
                    coleccion.insertaDocente(laboral);
                    for (Tutoria tutoria : tutorias) {
                        tutoria.setProfesor(laboral);
                        coleccion.insertaTutoria(tutoria);
                    }
                }
                break;
            default:
                System.err.println("Opcion no valida");
        }

    }

    /**
     * Modifica los datos propios de un funcionario
     *
     * @param d el funcionario a modificar
     */
    private void modificarFuncionario(Funcionario d) {
        System.out.println("Toma de posesion :");
        System.out.print("[" + d.getTomaPosesion().toString() + "]\n");
        modificarFecha(d.getTomaPosesion());
    }

    /**
     * Modifica los datos propios de un Laboral
     *
     * @param d el Laboral a modificar
     */
    private void modificarLaboral(Laboral d) {
        boolean flag = true;
        do {
            try {
                System.out.println("Fecha de Inicio: ");
                System.out.print("[" + d.getFechaInicio().toString() + "]\n");
                modificarFecha(d.getFechaInicio());
                System.out.println("Fecha de Fin: ");
                System.out.print("[" + d.getFechaFin().toString() + "]\n");
                modificarFecha(d.getFechaFin());
                d.setFechaFin(d.getFechaFin());//Comprobar que las fechas sean validas
                flag = false;
            } catch (FechaInvalidaException e) {
                System.err.println("Fecha Invalida: " + e.getMessage());
            }
        } while (flag);
    }

    /**
     * Modifica los datos basicos de un docente
     *
     * @param d el docente a modificar
     */
    private void modificarDocenteBasico(Docente d, Universidad coleccion) {
        String info;
        boolean vacio = false;

        modificaPersona(d);
        // Despacho
        System.out.print("Despacho ");
        if (Integer.toString(d.getDespacho()).length() > 0) {
            System.out.print("[" + d.getDespacho() + "]");
            vacio = true;
        }
        info = leeCadena(": ", vacio);
        if (info.length() > 0) {
            d.setDespacho(Integer.parseInt(info));
        }

        boolean flag = true;
        do {
            try {
                // NRP
                System.out.print("NRP");
                if (d.getNRP().length() > 0) {
                    System.out.print("[" + d.getNRP() + "]");
                    vacio = true;
                }
                info = leeCadena(": ", vacio);
                if (info.length() > 0) {
                    if (coleccion.buscarDocente(info) != -1) {
                        throw new NRPInvalidoException("El NRP introducido corresponde a otro docente");
                    }
                    d.setNRP(info);
                }
                flag = false;
            } catch (NRPInvalidoException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } while (flag);

    }

    /**
     * Lee del teclado la posición de un docente en la colección
     *
     * @param coleccion La colección de la que se obtiene el max.
     * @return la posición del docente, como entero.
     */
    private int leePosDocente(Universidad coleccion) {
        final int numDocentes = coleccion.getNumDocentes();
        int toret;

        do {
            toret = leeEntero("Introduzca posición del docente (1..."
                    + numDocentes + "): ");

            if (toret < 1 || toret > numDocentes) {
                System.err.println("Posición inválida. "
                        + "Por favor, introdúzcala de nuevo.");
            }
        } while (toret < 1 || toret > numDocentes);

        return toret - 1;
    }

    /**
     * Visualiza los docentes almacenados en la coleccion por la salida std.
     *
     * @param coleccion El objeto Universidad del que visualizar sus docentes.
     */
    private void listarDocentes(Universidad coleccion) {
        final int numDocentes = coleccion.getNumDocentes();

        if (numDocentes > 0) {
            for (int i = 0; i < numDocentes; i++) {
                System.out.print((i + 1) + ". ");
                System.out.println(coleccion.getDocente(i).toString());
                System.out.println("\n");
            }
        } else {
            System.out.println("No hay docentes.");
        }

    }

    /**
     * Visualiza los docentes de cierto tipo almacenados en la coleccion por la
     * salida std.
     *
     * @param coleccion El objeto Universidad del que visualizar sus docentes.
     */
    private void listarDocentesPorTipo(Universidad coleccion) {
        final int numDocentes = coleccion.getNumDocentes();

        int opt = leerClaseDocente("De que clase se listaran los docentes? ");

        if (numDocentes > 0) {
            StringBuilder sb = new StringBuilder();
            switch (opt) {
                case 0: //Funcionario
                    for (int i = 0; i < numDocentes; i++) {
                        if (coleccion.getDocente(i) instanceof Funcionario) {
                            sb.append((i + 1)).append(". ");
                            sb.append(coleccion.getDocente(i).toString());
                            sb.append("\n");
                        }
                    }
                    break;
                case 1: //Laboral
                    for (int i = 0; i < numDocentes; i++) {
                        if (coleccion.getDocente(i) instanceof Laboral) {
                            sb.append((i + 1)).append(". ");
                            sb.append(coleccion.getDocente(i).toString());
                            sb.append("\n");
                        }
                    }
                    break;
                default:
                    throw new AssertionError();
            }
            if (sb.isEmpty()) {
                System.out.println("No hay Docentes del tipo seleccionado");
            } else {
                System.out.println(sb.toString());
            }
        } else {
            System.out.println("No hay docentes.");
        }

    }

    /**
     * INTERFAZ/LÓGICA/CONTROL ESTUDIANTES *
     */
    /**
     * Muestra las distintas opciones para gestionar los estudiantes y lanza los
     * métodos que realiza cada operación
     *
     * @param coleccion La Universidad sobre la que actua
     */
    private void gestionEstudiantes(Universidad coleccion) {
        int opcion;

        do {
            opcion = menuGestionEstudiantes();
            switch (opcion) {
                case 1:
                    insertaEstudiante(coleccion);
                    break;
                case 2:
                    modificaEstudiante(coleccion);
                    break;
                case 3:
                    eliminaEstudiante(coleccion);
                    break;
                case 4:
                    listarEstudiantes(coleccion);
                    break;
            }
        } while (opcion != 0);
    }

    /**
     * Presenta un menu con las opciones para gestionar estudiantes, y permite
     * seleccionar una.
     *
     * @param coleccion La Universidad sobre la que se realizan las operaciones
     * @return la opcion seleccionada, como entero
     */
    private int menuGestionEstudiantes() {
        int toret;

        do {
            System.out.println("GESTION ESTUDIANTES");
            System.out.println(
                    "\n1. Inserta un nuevo estudiante\n"
                    + "2. Modifica un estudiante\n"
                    + "3. Elimina un estudiante\n"
                    + "4. Listar estudiantes\n"
                    + "0. Volver al menú principal\n");
            toret = leeEntero("Selecciona: ");
        } while (toret < 0
                || toret > 4);

        System.out.println();
        return toret;
    }

    /**
     * Crea un nuevo estudiante y lo inserta en la coleccion
     *
     * @param coleccion La coleccion en la que se inserta el estudiante.
     */
    private void insertaEstudiante(Universidad coleccion) {
        System.out.println("\n-------------");
        System.out.println("\nAlta estudiante");

        Estudiante e = leeEstudiante();
        try {
            coleccion.insertaEstudiante(e);
        } catch (MatriculaInvalidaException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    /**
     * Lee del teclado los datos de un estudiante.
     *
     * @return El objeto Estudiante creado
     */
    private Estudiante leeEstudiante() {

        System.out.println("\nIntroduce los datos del nuevo estudiante:");

        //Lectura de datos de persona
        int dni = leeEntero("\tD.N.I.: ");
        String nombre = leeCadena("\tNombre: ", false);

        String numMatricula = leeCadena("\tNum. Matricula: ", false);

        return new Estudiante(numMatricula, nombre, dni);
    }

    /**
     * Borra un estudiante por su posicion en la colección.
     *
     * @param coleccion La coleccion en la que se elimina el estudiante
     */
    private void eliminaEstudiante(Universidad coleccion) {
        if (coleccion.getNumEstudiantes() > 0) {
            try {
                coleccion.eliminaEstudiante(leePosEstudiante(coleccion));
            } catch (NoEliminableException ex) {
                System.err.println("Estudiante no Eliminable: " + ex.getMessage());
            }
        } else {
            System.out.println("La coleccion no contiene estudiantes.");
        }
    }

    /**
     * Modifica un estudiante existente.
     *
     * @param coleccion La coleccion de la cual modificar un estudiante.
     */
    private void modificaEstudiante(Universidad coleccion) {
        System.out.println("\n---------------------");
        System.out.println("\nModificación estudiante");

        if (coleccion.getNumEstudiantes() > 0) {
            this.modificaEstudiante(coleccion.getEstudiante(this.leePosEstudiante(coleccion)), coleccion);
        } else {
            System.out.println("La coleccion no contiene estudiantes.");
        }
    }

    private void modificaEstudiante(Estudiante e, Universidad coleccion) {
        boolean vacio = false;
        String info;

        System.out.println("\nModificando los datos del siguiente estudiante:");
        System.out.println(e.toString());
        System.out.println();

        modificaPersona(e);

        boolean flag = true;
        do {
            try {
                // Num Matricula
                System.out.print("Num matricula.");
                if (e.getNumMatricula().length() > 0) {
                    System.out.print("[" + e.getNumMatricula() + "]");
                    vacio = true;
                }
                info = leeCadena(": ", vacio);
                if (info.length() > 0) {
                    if (coleccion.buscarEstudiante(info) != -1) {
                        throw new MatriculaInvalidaException("El numero de "
                                + "matricula corresponde a otro estudiante");
                    }
                    e.setNumMatricula(info);
                }

                flag = false;
            } catch (MatriculaInvalidaException ex) {
                System.err.println("Error: " + ex.getMessage());
            }
        } while (flag);

    }

    /**
     * Lee del teclado la posición de un estudiante en la colección
     *
     * @param coleccion La colección de la que se obtiene el max.
     * @return la posición del estudiante, como entero.
     */
    private int leePosEstudiante(Universidad coleccion) {
        final int numEstudiantes = coleccion.getNumEstudiantes();
        int toret;

        do {
            toret = leeEntero("Introduzca posición del estudiante (1..."
                    + numEstudiantes + "): ");

            if (toret < 1 || toret > numEstudiantes) {
                System.err.println("Posición inválida. "
                        + "Por favor, introdúzcala de nuevo.");
            }
        } while (toret < 1 || toret > numEstudiantes);

        return toret - 1;
    }

    /**
     * Visualiza los estudiantes almacenados en la coleccion por la salida std.
     *
     * @param coleccion El objeto Universidad del que visualizar sus
     * estudiantes.
     */
    private void listarEstudiantes(Universidad coleccion) {
        final int numEstudiantes = coleccion.getNumEstudiantes();

        if (numEstudiantes > 0) {
            for (int i = 0; i < numEstudiantes; i++) {
                System.out.print((i + 1) + ". ");
                System.out.println(coleccion.getEstudiante(i).toString());
                System.out.println("\n");
            }
        } else {
            System.out.println("No hay estudiantes.");
        }

    }

    /**
     * INTERFAZ/LÓGICA/CONTROL TUTORIAS
     *
     */
    /**
     * Muestra las distintas opciones para gestionar las tutorias y lanza los
     * métodos que realiza cada operación
     *
     * @param coleccion La Universidad sobre la que actua
     */
    private void gestionTutorias(Universidad coleccion) {
        int opcion;

        do {
            opcion = menuGestionTutorias();
            switch (opcion) {
                case 1:
                    insertaTutoria(coleccion);
                    break;
                case 2:
                    modificaTutoria(coleccion);
                    break;
                case 3:
                    eliminaTutoria(coleccion);
                    break;
                case 4:
                    listarTutorias(coleccion);
                    break;
            }
        } while (opcion != 0);
    }

    /**
     * Presenta un menu con las opciones para gestionar tutorias, y permite
     * seleccionar una.
     *
     * @param coleccion La Universidad sobre la que se realizan las operaciones
     * @return la opcion seleccionada, como entero
     */
    private int menuGestionTutorias() {
        int toret;

        do {
            System.out.println("GESTION TUTORIAS");
            System.out.println(
                    "\n1. Inserta una nueva tutoria\n"
                    + "2. Modifica una tutoria\n"
                    + "3. Elimina una tutoria\n"
                    + "4. Listar tutorias\n"
                    + "0. Volver al menú principal\n");
            toret = leeEntero("Selecciona: ");
        } while (toret < 0
                || toret > 4);

        System.out.println();
        return toret;
    }

    /**
     * Crea una nueva tutoria y la inserta en la coleccion
     *
     * @param coleccion La coleccion en la que se inserta la tutoria.
     */
    private void insertaTutoria(Universidad coleccion) {
        System.out.println("\n-------------");
        System.out.println("\nAlta tutoria");
        if (coleccion.getNumDocentes() != 0 && coleccion.getNumEstudiantes() != 0) {
            Tutoria t = leeTutoria(coleccion);
            coleccion.insertaTutoria(t);

        } else {
            System.out.println("No se pueden hacer tutorias si no hay profesores/estudiantes");
        }

    }

    /**
     * Lee del teclado los datos de una Tutoria.
     *
     * @return El objeto Tutoria creado
     */
    private Tutoria leeTutoria(Universidad coleccion) {
        System.out.println("\nIntroduce los datos de la nueva tutoria:");

        Docente profesor = elegirDocenteTutoria(coleccion, "NRP del Docente que imparte la tutoria:");
        Estudiante estudiante = elegirEstudianteTutoria(coleccion, "Matricula del Estudiante que solicita la tutoria: ");
        Fecha fecha = leerFecha("Fecha de la tutoria: ");
        Hora hora = leerHora("Hora de la tutoria: ");

        return new Tutoria(profesor, estudiante, fecha, hora);
    }

    /**
     * Elige un docente para una tutoria
     *
     * @param coleccion la coleccion que contiene a los Docentes validos
     * @param msg El mensaje a mostrar
     * @return el Docente
     */
    private Docente elegirDocenteTutoria(Universidad coleccion, String msg) {
        int pos;
        do {
            listarDocentes(coleccion);
            String nrp = leeCadena(msg, false);
            pos = coleccion.buscarDocente(nrp);
            if (pos == -1) {
                System.out.println("No se ha encontrado un Docente con ese NRP");
            }
        } while (pos == -1);
        return coleccion.getDocente(pos);
    }

    /**
     * Elige un docente para una tutoria
     *
     * @param coleccion la coleccion que contiene a los Docentes validos
     * @param msg El mensaje a mostrar
     * @return el Docente
     */
    private Estudiante elegirEstudianteTutoria(Universidad coleccion, String msg) {
        int pos;
        do {
            listarEstudiantes(coleccion);
            String numMatricula = leeCadena(msg, false);
            pos = coleccion.buscarEstudiante(numMatricula);
            if (pos == -1) {
                System.out.println("No se ha encontrado un Estudiante con ese numMatricula");
            }
        } while (pos == -1);
        return coleccion.getEstudiante(pos);
    }

    /**
     * Borra una Tutoria por su posicion en la colección.
     *
     * @param coleccion La coleccion en la que se elimina la tutoria
     */
    private void eliminaTutoria(Universidad coleccion) {
        if (coleccion.getNumTutorias() > 0) {
            coleccion.eliminaTutoria(leePosTutoria(coleccion));
        } else {
            System.out.println("La coleccion no contiene tutorias.");
        }
    }

    /**
     * Modifica una tutoria existente.
     *
     * @param coleccion La coleccion de la cual modificar una tutoria.
     */
    private void modificaTutoria(Universidad coleccion) {
        System.out.println("\n---------------------");
        System.out.println("\nModificación tutoria");

        if (coleccion.getNumTutorias() > 0) {
            this.modificaTutoria(coleccion.getTutoria(this.leePosTutoria(coleccion)), coleccion);
        } else {
            System.out.println("La coleccion no contiene tutorias.");
        }
    }

    private void modificaTutoria(Tutoria t, Universidad coleccion) {

        boolean vacio;
        String info;

        //Docente
        System.out.print("Profesor. ");
        System.out.print("[" + t.getProfesor().toString() + "]");
        vacio = true;
        info = leeCadena("Pulse S para cambiar Profesor: ", vacio);
        if (info.toUpperCase().charAt(0) == 'S') {
            t.setProfesor(elegirDocenteTutoria(coleccion, "Introduzca el NRP del nuevo profesor"));
        }

        //Estudiante
        System.out.print("Estudiante. ");
        System.out.print("[" + t.getProfesor().toString() + "]");
        vacio = true;
        info = leeCadena("Pulse S para cambiar el Estudiante: ", vacio);
        if (info.toUpperCase().charAt(0) == 'S') {
            t.setEstudiante(elegirEstudianteTutoria(coleccion, "Introduzca el "
                    + "numero de matricula del nuevo estudiante"));
        }

        //Hora
        System.out.println("Hora :");
        System.out.print("[" + t.getHoraTutoria().toString() + "]\n");
        modificarHora(t.getHoraTutoria());

        //Fecha
        System.out.println("Fecha :");
        System.out.print("[" + t.getFechaTutoria().toString() + "]\n");
        modificarFecha(t.getFechaTutoria());

    }

    /**
     * Lee del teclado la posición de una tutoria en la colección
     *
     * @param coleccion La colección de la que se obtiene el max.
     * @return la posición de la tutoria, como entero.
     */
    private int leePosTutoria(Universidad coleccion) {
        final int numTutorias = coleccion.getNumTutorias();
        int toret;

        do {
            toret = leeEntero("Introduzca posición del estudiante (1..."
                    + numTutorias + "): ");

            if (toret < 1 || toret > numTutorias) {
                System.err.println("Posición inválida. "
                        + "Por favor, introdúzcala de nuevo.");
            }
        } while (toret < 1 || toret > numTutorias);

        return toret - 1;
    }

    /**
     * Visualiza las tutorias almacenados en la coleccion por la salida std.
     *
     * @param coleccion El objeto Universidad del que visualizar sus tutorias.
     */
    private void listarTutorias(Universidad coleccion) {
        final int numTutorias = coleccion.getNumTutorias();

        if (numTutorias > 0) {
            for (int i = 0; i < numTutorias; i++) {
                System.out.print((i + 1) + ". ");
                System.out.println(coleccion.getTutoria(i).toString());
                System.out.println("\n");
            }
        } else {
            System.out.println("No hay tutorias.");
        }

    }

    /**
     * Modifica los datos basicos de una Persona
     *
     * @param p la persona a modificar
     */
    private void modificaPersona(Persona p) {
        boolean vacio;
        String info;

        // D.N.I.
        System.out.print("DNI. ");
        System.out.print("[" + p.getDni() + "]");
        vacio = true;

        info = leeCadena(": ", vacio);
        if (info.length() > 0) {
            p.setDni(Integer.parseInt(info));
        }

        // Nombre
        System.out.print("Nombre ");
        if (p.getNombre().length() > 0) {
            System.out.print("[" + p.getNombre() + "]");
            vacio = true;
        }
        info = leeCadena(": ", vacio);
        if (info.length() > 0) {
            p.setNombre(info);
        }
    }

    /**
     * Lee una Fecha por teclado
     *
     * @param msg el mensaje a mostrar
     * @return la Fecha leida
     */
    private Fecha leerFecha(String msg) {
        boolean flag = true;
        Fecha f = null;
        do {
            try {
                System.out.println(msg);
                int dia = leeEntero("\t dia: ");
                int mes = leeEntero("\t mes: ");
                int ano = leeEntero("\t ano: ");
                f = new Fecha(dia, mes, ano);
                flag = false;
            } catch (FechaException e) {
                System.err.println("Fecha invalida: " + e.getMessage());
            }
        } while (flag);
        return f;
    }

    /**
     * Lee una Hora por teclado
     *
     * @param msg el mensaje a mostrar
     * @return la Hora leida
     */
    private Hora leerHora(String msg) {
        boolean flag = true;
        Hora h = null;
        do {
            try {
                System.out.println(msg);
                int hora = leeEntero("\t hora: ");
                int min = leeEntero("\t minuto: ");
                h = new Hora(hora, min);
                flag = false;
            } catch (HoraException e) {
                System.err.println("Hora invalida: " + e.getMessage());
            }
        } while (flag);
        return h;
    }

    /**
     * Modifica una fecha
     *
     * @param f la fecha a modificar
     */
    private void modificarFecha(Fecha f) {
        boolean vacio;
        String info;
        boolean flag = true;
        do {
            try {
                // Dia
                System.out.print("Dia. ");
                System.out.print("[" + f.getDia() + "]");
                vacio = true;
                info = leeCadena(": ", vacio);
                if (info.length() > 0) {
                    f.setDia(Integer.parseInt(info));
                }
                flag = false;

            } catch (FechaException e) {
                System.err.println("Fecha invalida: " + e.getMessage());
            }
        } while (flag);

        flag = false;
        do {
            try {
                // Mes
                System.out.print("Mes. ");
                System.out.print("[" + f.getMes() + "]");
                vacio = true;
                info = leeCadena(": ", vacio);
                if (info.length() > 0) {
                    f.setMes(Integer.parseInt(info));
                }
                flag = false;

            } catch (FechaException e) {
                System.err.println("Fecha invalida: " + e.getMessage());
            }
        } while (flag);

        // Año
        System.out.print("Ano. ");
        System.out.print("[" + f.getAno() + "]");
        vacio = true;
        info = leeCadena(": ", vacio);
        if (info.length() > 0) {
            f.setAno(Integer.parseInt(info));
        }

    }

    /**
     * Modifica una Hora
     *
     * @param h la Hora a modificar
     */
    private void modificarHora(Hora h) {
        boolean vacio;
        String info;
        boolean flag = true;
        do {
            try {
                // Minuto
                System.out.print("Minuto. ");
                System.out.print("[" + h.getMin() + "]");
                vacio = true;
                info = leeCadena(": ", vacio);
                if (info.length() > 0) {
                    h.setMin(Integer.parseInt(info));
                }
                flag = false;
            } catch (HoraException e) {
                System.err.println("Hora invalida: " + e.getMessage());
            }
        } while (flag);

        flag = true;
        do {
            try {
                // Hora
                System.out.print("Hora. ");
                System.out.print("[" + h.getHora() + "]");
                vacio = true;
                info = leeCadena(": ", vacio);
                if (info.length() > 0) {
                    h.setHora(Integer.parseInt(info));
                }
                flag = false;
            } catch (HoraException e) {
                System.err.println("Hora invalida: " + e.getMessage());
            }
        } while (flag);

    }

}
