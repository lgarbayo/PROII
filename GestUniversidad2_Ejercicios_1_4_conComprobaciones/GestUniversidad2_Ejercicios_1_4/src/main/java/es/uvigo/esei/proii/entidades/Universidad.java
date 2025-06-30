package es.uvigo.esei.proii.entidades;

import es.uvigo.esei.proii.excepciones.ExisteDocenteNrpException;
import es.uvigo.esei.proii.excepciones.ExisteEstudianteException;
import java.util.ArrayList;
import java.util.List;

public class Universidad {

    private String nombre;
    private List<Docente> docentes;
    private List<Estudiante> estudiantes;
    private List<Tutoria> tutorias;

    public Universidad(String nombre, int numMaxDocentes,
            int numMaxEstudiantes, int numMaxTutorias) {
        this.nombre = nombre;
        docentes = new ArrayList<>(numMaxDocentes);
        estudiantes = new ArrayList<>(numMaxEstudiantes);
        tutorias = new ArrayList<>(numMaxTutorias);
    }

    public String getNombre() {
        return nombre;
    }

    public String toString() {

        StringBuilder toret = new StringBuilder();

        toret.append("La Universidad ").append(getNombre())
                .append(" tiene: \n");
        toret.append((docentes.size() == 0) ? "\n\t 0 Docentes\n" : "\nDOCENTES\n");
        for (Docente docente : docentes) {
            toret.append(docente.toString());
        }

        toret.append((estudiantes.size() == 0) ? "\n\t 0 Estudiantes\n" : "\nESTUDIANTES\n");
        for (Estudiante alumno : estudiantes) {
            toret.append(alumno.toString());
        }

        toret.append((tutorias.size() == 0) ? "\n\t 0 Tutorías\n" : "\nTUTORIAS\n");
        for (Tutoria tutoria : tutorias) {
            toret.append(tutoria.toString());
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
        return docentes.size();
    }

    /**
     * Devuelve el docente situado en pos
     *
     * @param pos el lugar del docente en el vector de docentes
     * @return el objeto Docente correspondiente.
     */
    public Docente getDocente(int pos) {
        if (pos < 0 || pos >= getNumDocentes()) {
            throw new IllegalArgumentException("getDocente(): esa posicion"
                    + (pos + 1) + "sobrepasa el número de docentes. ");
        }

        return docentes.get(pos);
    }
    
    public int esUnicoNrp (String nrp){
        int numDocentes = getNumDocentes();
        int cont = 0;
        while(cont < numDocentes && !(getDocente(cont).getNrp().equals(nrp)) ){
           cont++;
        }
        return cont;
    }
    
    /**
     * Inserta un nuevo docente.
     *
     * @param p el nuevo objeto Docente
     */
    public void insertaDocente( Docente p){
        if(esUnicoNrp(p.getNrp()) == getNumDocentes()){
            docentes.add(p);
        }
        else{
            throw new ExisteDocenteNrpException("Se ha introducido un docente ya registrado");
        }
    }

    /**
     * Elimina el docente que se encuentra en la posición indicada.
     *
     * @param pos la posición del docente a eliminar
     */
    public void eliminaDocente(int pos) {
        if (pos < 0 || pos >= getNumDocentes()) {
            throw new IllegalArgumentException("Error. No existe ningun "
                    + "docente en esa "
                    + "posicion (" + pos + ")");
        }
        docentes.remove(pos);
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
        return estudiantes.size();
    }

    /**
     * Devuelve el estudiante situado en pos
     *
     * @param pos el lugar del estudiante en el vector de estudiantes
     * @return el objeto Estudiante correspondiente.
     */
    public Estudiante getEstudiante(int pos) {
        if (pos < 0 || pos >= getNumEstudiantes()) {
            throw new IllegalArgumentException("getEstudiante(): esa posicion "
                    + (pos + 1) + " sobrepasa el numero de estudiantes");
        }

        return estudiantes.get(pos);
    }

    /**
     * Inserta un nuevo estudiante.
     *
     * @param e el nuevo objeto Estudiante
     */
    public void insertaEstudiante(Estudiante e) {
        if (comprobarMatricula (e.getNumeroMatricula()) < this.getNumEstudiantes()) {      
        throw new ExisteEstudianteException ("Ya existe el estudiante");
        } else {
           estudiantes.add(e);
        }
    }
    
    public int comprobarMatricula (String e) {
        int i = 0;
        int num = this.getNumEstudiantes();
        while (i < num && !e.equals(estudiantes.get(i).getNumeroMatricula())) {
            i++;
        }
        return i;
    }

    /**
     * Elimina el estudiante que se encuentra en la posición indicada.
     *
     * @param pos la posición del estudiante a eliminar
     */
    public void eliminaEstudiante(int pos) {
        if (pos < 0 || pos >= getNumEstudiantes()) {
            throw new IllegalArgumentException("Error. No existe ningun "
                    + "estudiante en esa "
                    + "posicion (" + pos + ")");
        }
        estudiantes.remove(pos);
    }

    /**
     * GESTIÓN DE TUTORIAS
     *
     */
    /**
     * Devuelve el num. de tutorias creadas.
     *
     * @return el num. de tutorias existentes, como entero.
     */
    public int getNumTutorias() {
        return tutorias.size();
    }

    /**
     * Devuelve la tutoría situado en pos
     *
     * @param pos el lugar de la tutoría en el vector de tutorias
     * @return el objeto Tutoria correspondiente.
     */
    public Tutoria getTutoria(int pos) {
        if (pos < 0 || pos >= getNumTutorias()) {
            throw new IllegalArgumentException("getTutoria(): esa posicion "
                    + (pos + 1) + " sobrepasa el numero de tutorias");
        }

        return tutorias.get(pos);
    }

    /**
     * Inserta una nueva tutoria.
     *
     * @param t el nuevo objeto Tutoria
     */
    public void insertaTutoria(Tutoria t) {

        tutorias.add(t);
    }

    /**
     * Elimina la tutoría que se encuentra en la posición indicada.
     *
     * @param pos la posición de la tutoría a eliminar
     */
    public void eliminaTutoria(int pos) {
        if (pos < 0 || pos >= getNumTutorias()) {
            throw new IllegalArgumentException("Error. No existe ninguna "
                    + "tutoría en esa "
                    + "posicion (" + pos + ")");
        }
        tutorias.remove(pos);
    }
}
