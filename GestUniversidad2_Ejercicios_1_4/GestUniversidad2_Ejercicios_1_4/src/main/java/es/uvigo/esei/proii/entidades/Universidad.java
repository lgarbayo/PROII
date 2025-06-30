package es.uvigo.esei.proii.entidades;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.Serializer;

public class Universidad {

    private String nombre;
    private List<Docente> docentes;
    private List<Estudiante> estudiantes;
    private List<Tutoria> tutorias;
    private static final String TAG_UNI = "Universidad";
    private static final String TAG_NOMBRE = "Nombre";
    private static final String TAG_DOCENTES = "Docentes";
    private static final String TAG_ESTUDIANTES = "Estudiantes";
    private static final String TAG_TUTORIAS = "Tutorias";

    public Universidad(String nombre, int numMaxDocentes,
            int numMaxEstudiantes, int numMaxTutorias) {
        this.nombre = nombre;
        docentes = new ArrayList<>(numMaxDocentes);
        estudiantes = new ArrayList<>(numMaxEstudiantes);
        tutorias = new ArrayList<>(numMaxTutorias);
    }

    /**
     * Construye una universidad a partir de un fichero XML que contiene TODOS sus datos
     * @param nombreFichero
     * @throws ParsingException
     * @throws IOException 
     */
    public Universidad(String nombreFichero) throws ParsingException, IOException{
        Builder parser = new Builder();
        Document doc = parser.build(new File(nombreFichero));
        
        Element eUni = doc.getRootElement();
        Element eNombre = eUni.getFirstChildElement(TAG_NOMBRE);
        if(eNombre == null){
            throw new ParsingException("No se ha encontrado la tag "+TAG_NOMBRE);
        }
        this.nombre = eNombre.getValue().trim();
        
        Elements eDocentes = eUni.getFirstChildElement(TAG_DOCENTES).getChildElements();
        int numDocentes = eDocentes.size();
        this.docentes = new ArrayList<>(numDocentes);
        for (int i = 0; i < numDocentes; i++) {
            Docente d = null;
            Element eDocente = eDocentes.get(i);
            if (eDocente.getLocalName().equals(Laboral.TAG_LABORAL)) {
                d = new Laboral(eDocente);
            }
            if (eDocente.getLocalName().equals(Funcionario.TAG_FUNCIONARIO)) {
                d = new Funcionario(eDocente);
            }
            if(d == null){
                throw new ParsingException("Error al leer un Docente");
            }
            insertaDocente(d);
        }
        
        Elements eEstudiantes = eUni.getFirstChildElement(TAG_ESTUDIANTES).getChildElements();
        int numEstudiantes = eEstudiantes.size();
        this.estudiantes = new ArrayList<>(numEstudiantes);
        for (int i = 0; i < numEstudiantes; i++) {
            insertaEstudiante(new Estudiante(eEstudiantes.get(i)));
        }
        
        Elements eTutorias = eUni.getFirstChildElement(TAG_TUTORIAS).getChildElements();
        int numTutorias = eTutorias.size();
        this.tutorias = new ArrayList<>(numTutorias);
        for (int i = 0; i < numTutorias; i++) {
            insertaTutoria(new Tutoria(eTutorias.get(i)));
        }
        
    }
    
    /**
     * Construye un auniversidad a partir de varios ficheros XML
     * @param nombre
     * @param ficheroDocentes
     * @param ficheroEstudiantes
     * @param ficheroTutorias
     * @throws ParsingException
     * @throws IOException 
     */
    public Universidad(String nombre,String ficheroDocentes,String ficheroEstudiantes,String ficheroTutorias) throws ParsingException, IOException{
        
        this.setNombre(nombre);
        
        Builder parser = new Builder();
        
        Document docD = parser.build(new File(ficheroDocentes));
        Elements eDocentes = docD.getRootElement().getChildElements();
        int numDocentes = eDocentes.size();
        
        this.docentes = new ArrayList<>(numDocentes);
        
        for (int i = 0; i < numDocentes; i++) {
            Docente d;
            Element eDocente = eDocentes.get(i);
            if (eDocente.getLocalName().equals(Laboral.TAG_LABORAL)) {
                d = new Laboral(eDocente);
            }else{
                if (eDocente.getLocalName().equals(Funcionario.TAG_FUNCIONARIO)) {
                    d = new Funcionario(eDocente);
                }else{
                    //No se ha leido
                    throw new ParsingException("Error al leer el fichero "+ficheroDocentes);
                }
            }
            insertaDocente(d);
        }
        Document docE = parser.build(new File(ficheroEstudiantes));
        Elements eEstudiantes = docE.getRootElement().getChildElements();
        int numEstudiantes = eEstudiantes.size();
        this.estudiantes = new ArrayList<>(numEstudiantes);
        for (int i = 0; i < numEstudiantes; i++) {
            insertaEstudiante(new Estudiante(eEstudiantes.get(i)));
        }
        
        Document docT = parser.build(new File(ficheroTutorias));
        Elements eTutorias = docT.getRootElement().getChildElements();
        int numTutorias = eTutorias.size();
        this.tutorias = new ArrayList<>(numTutorias);
        for (int i = 0; i < numTutorias; i++) {
            insertaTutoria(new Tutoria(eTutorias.get(i)));
        }
        
    }
    
    /**
     * Crea un fichero con TODOS los datos de la universidad
     * @param nombreFichero
     * @throws IOException 
     */
    public void toXML(String nombreFichero) throws IOException{
        FileOutputStream f = new FileOutputStream(nombreFichero);
        Serializer serial = new Serializer(f);
        Document doc = new Document(this.toDOM());
        serial.write(doc);
        f.close();        
    }
    
    
    /**
     * Crea un fichero por cada arrayList de la universidad
     * @param ficheroDocentes
     * @param ficheroEstudiantes
     * @param ficheroTutorias
     * @throws IOException 
     */
    public void toXML(String ficheroDocentes,String ficheroEstudiantes,String ficheroTutorias) throws IOException{
        Element eDoc = this.docentesToDOM();
        Element eEstudiantes = this.estudiantesToDOM();
        Element eTutorias = this.tutoriasToDOM();
        
        
        FileOutputStream fD = new FileOutputStream(ficheroDocentes);
        Serializer serial = new Serializer(fD);
        Document doc = new Document(eDoc);
        serial.write(doc);
        fD.close(); 
        
        FileOutputStream fE = new FileOutputStream(ficheroEstudiantes);
        Serializer serial2 = new Serializer(fE);
        Document doc2 = new Document(eEstudiantes);
        serial2.write(doc2);
        fE.close();
        
        FileOutputStream fT = new FileOutputStream(ficheroTutorias);
        Serializer serial3 = new Serializer(fT);
        Document doc3 = new Document(eTutorias);
        serial3.write(doc3);
        fT.close();
    }
    
    public Element docentesToDOM(){
        Element eDocentes = new Element(TAG_DOCENTES);
        for (Docente docente : docentes) {
            eDocentes.appendChild(docente.toDOM());
        }
        return eDocentes;
    }
    
    public Element estudiantesToDOM(){
        Element eEstudiantes = new Element(TAG_ESTUDIANTES);
        for (Estudiante e : estudiantes) {
            eEstudiantes.appendChild(e.toDOM());
        }
        return eEstudiantes;
    }
    
    public Element tutoriasToDOM(){
        Element eTutorias = new Element(TAG_TUTORIAS);

        for (Tutoria t : tutorias) {
            eTutorias.appendChild(t.toDOM());
        }
        return eTutorias;
    }
    
    public Element toDOM(){
        Element raiz = new Element(TAG_UNI);
        Element eDocentes = docentesToDOM();
        Element eEstudiantes = estudiantesToDOM();
        Element eTutorias = tutoriasToDOM();
        
        raiz.appendChild(eDocentes);
        
        
        raiz.appendChild(eEstudiantes);
        
        raiz.appendChild(eTutorias);
        
        Element eNombre = new Element(TAG_NOMBRE);
        eNombre.appendChild(nombre);
        raiz.appendChild(eNombre);
        
        return raiz;    
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    /**
     * Inserta un nuevo docente.
     *
     * @param p el nuevo objeto Docente
     */
    public void insertaDocente(Docente p) {

        docentes.add(p);
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

        estudiantes.add(e);
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
