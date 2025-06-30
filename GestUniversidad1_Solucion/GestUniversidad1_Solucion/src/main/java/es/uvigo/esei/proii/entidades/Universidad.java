package es.uvigo.esei.proii.entidades;

public class Universidad {

    private String nombre;
    private Docente[] docentes;
    private int numDocentes;
    private Estudiante[] estudiantes;
    private int numEstudiantes;

    public Universidad(String nombre, int numMaxDocentes,
            int numMaxEstudiantes) {
        this.nombre = nombre;
        docentes = new Docente[numMaxDocentes];
        numDocentes = 0;
        estudiantes = new Estudiante[numMaxEstudiantes];
        numEstudiantes = 0;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String toString(){
        int capacidadHoras = 0;
        int numDocentes = getNumDocentes();
        int numEstudiantes = getNumEstudiantes();
        StringBuilder toret = new StringBuilder();
        
        toret.append("La Universidad ").append(getNombre())
                .append(" tiene: \n" );
        toret.append((numDocentes == 0) ? "\n\t 0 Docentes\n" : "\nDOCENTES\n");
        for (int i = 0; i < numDocentes; i++) {
            toret.append(getDocente(i).toString());
            capacidadHoras += getDocente(i).getDedicacion().getNumHoras();
        }
        toret.append("\nLa capacidad total de horas de los docentes es: ")
                .append(capacidadHoras).append("\n");
        toret.append((numEstudiantes == 0) ? "\n\t 0 Estudiantes\n" : "\nESTUDIANTES\n");
        for (int i = 0; i < numEstudiantes; i++) {
            toret.append(getEstudiante(i).toString());
        }
        
        return toret.toString();
    }

    /**
     * GESTIÓN DE DOCENTES
     *
     */
    /**
     * Devuelve el num. de docentes creados.
     *
     * @return el num. de docentes existentes, como entero.
     */
    public int getNumDocentes() {
        return numDocentes;
    }

    /**
     * Devuelve el max. de numDocentees
     *
     * @return el num. de docentes max,, como entero
     */
    public int getMaxDocentes() {
        return docentes.length;
    }

    /**
     * Devuelve el docente situado en pos
     *
     * @param pos el lugar del docente en el vector de docentes
     * @return el objeto Docente correspondiente.
     */
    public Docente getDocente(int pos) {
        if (pos < 0 || pos >= getNumDocentes()) {
            throw new IllegalArgumentException("get(): sobrepasa la pos: " 
                    + (pos + 1) + " / "
                    + getMaxDocentes());
        }

        return docentes[pos];
    }

    /**
     * Inserta un nuevo docente.
     *
     * @param p el nuevo objeto Docente
     */
    public void insertaDocente(Docente p) {
        if (getNumDocentes() == docentes.length) {
            throw new ArrayIndexOutOfBoundsException("Ya no queda espacio para "
                    + "mas docentes.");
        }
        docentes[numDocentes++] = p;
    }

    /**
     * Elimina el docente que se encuentra en la posición indicada.
     *
     * @param pos la posición del docente a eliminar
     */
    public void eliminaDocente(int pos) {
        if (pos < 0 || pos >= getNumDocentes()){
            throw new IllegalArgumentException("Error. No existe ningun "
                    + "docente en esa "
                    + "posicion (" + pos + ")");
        }
        docentes [pos] = docentes [ --numDocentes ];
    }

    /**
     * GESTIÓN DE ESTUDIANTES
     *
     */
    /**
     * Devuelve el num. de estudiantes creados.
     *
     * @return el num. de estudiantes existentes, como entero.
     */
    public int getNumEstudiantes() {
        return numEstudiantes;
    }

    /**
     * Devuelve el max. de numEstudiantes
     *
     * @return el num. de estudiantes max,, como entero
     */
    public int getMaxEstudiantes() {
        return estudiantes.length;
    }

    /**
     * Devuelve el estudiante situado en pos
     *
     * @param pos el lugar del estudiante en el vector de estudiantes
     * @return el objeto Estudiante correspondiente.
     */
    public Estudiante getEstudiante(int pos) {
        if (pos < 0 || pos >= getNumEstudiantes()) {
            throw new IllegalArgumentException("get(): sobrepasa la pos: "
                    + (pos + 1) + " / "
                    + getMaxEstudiantes());
        }

        return estudiantes[pos];
    }

    /**
     * Inserta un nuevo estudiante.
     *
     * @param e el nuevo objeto Estudiante
     */
    public void insertaEstudiante(Estudiante e) {
        if (getNumEstudiantes() == estudiantes.length) {
            throw new ArrayIndexOutOfBoundsException("Ya no queda espacio para mas "
                    + "estudiantes.");
        }
        estudiantes[numEstudiantes++] = e;
    }

    /**
     * Elimina el estudiante que se encuentra en la posición indicada.
     *
     * @param pos la posición del estudiante a eliminar
     */
    public void eliminaEstudiante(int pos) {
        if (pos < 0 || pos >= getNumEstudiantes()){
            throw new IllegalArgumentException("Error. No existe ningun "
                    + "estudiante en esa "
                    + "posicion (" + pos + ")");
        }
        estudiantes [pos] = estudiantes [ --numEstudiantes ];
    }
}
