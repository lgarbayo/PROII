/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.proii.core;


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

/**
 *
 * @author Nanny Glez
 */
public class Universidad {

    private String nombre;
    private ArrayList<Docente> docentes;
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Tutoria> tutorias;
  
    private static final String TAG_UNI = "Universidad";
    private static final String TAG_NOMBRE = "Nombre";
    private static final String TAG_DOCENTES = "Docentes";
    private static final String TAG_ESTUDIANTES = "Estudiantes";
    private static final String TAG_TUTORIAS = "Tutorias";

    public Universidad(String nombre, int numMaxDocentes,
            int numMaxEstudiantes, int numMaxTutorias) {
        this.nombre = nombre;
        this.docentes = new ArrayList<>(numMaxDocentes);
        this.estudiantes = new ArrayList<>(numMaxEstudiantes);
        this.tutorias = new ArrayList<>(numMaxTutorias);

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

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();
        StringBuilder toDocentes = new StringBuilder();
        StringBuilder toEstudiantes = new StringBuilder();
        StringBuilder toTutorias = new StringBuilder();
        toret.append("La universidad ").append(getNombre());

        if (getNumDocentes() == 0) {
            toDocentes.append("\nNo tiene docentes.");
        } else {
            toDocentes.append("\nDocentes: ");
            for (int i = 0; i < getNumDocentes(); i++) {
                toDocentes.append("\n\tDocente ").append(i + 1)
                        .append(": ").append(getDocente(i).toString());
            }
        }
        toret.append(toDocentes);

        if (getNumEstudiantes() == 0) {
            toEstudiantes.append("\nNo tiene estudiantes.");
        } else {
            toEstudiantes.append("\nEstudiantes: ");
            for (int i = 0; i < getNumEstudiantes(); i++) {
                toEstudiantes.append("\n\tEstudiante ").append(i + 1)
                        .append(": ").append(getEstudiante(i).toString());
            }
        }
        toret.append(toEstudiantes);
        
        if (getNumTutorias() == 0) {
            toTutorias.append("\nNo tiene Tutorias.");
        } else {
            toTutorias.append("\nTutorias: ");
            for (int i = 0; i < getNumTutorias(); i++) {
                toTutorias.append("\n\tTutoria ").append(i + 1)
                        .append(": ").append(getTutoria(i).toString());
            }
        }
        toret.append(toTutorias);

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
     * @throws IndexOutOfBoundsException si la posicion no es valida
     */
    public Docente getDocente(int pos) {
        return this.docentes.get(pos);
    }

    /**
     * Inserta un nuevo docente.
     *
     * @param p el nuevo objeto Docente
     */
    public void insertaDocente(Docente p) throws NRPInvalidoException {
        if(buscarDocente(p.getNRP()) != -1){
            throw new NRPInvalidoException("No se puede insertar un docente con un NRP repetido");
        }
        docentes.add(p);
    }

    /**
     * Elimina el docente que se encuentra en la posición indicada.
     *
     * @param pos la posición del docente a eliminar
     * @throws NoEliminableException si tiene tutorias 
     * @throws IndexOutOfBoundsException si la posicion no es valida
    */
    public void eliminaDocente(int pos) throws NoEliminableException{
        if (buscarTutoria(getDocente(pos)) != -1) {
            throw new NoEliminableException("El docente que se ha intentado eliminar tiene tutoria");
        }
        docentes.remove(pos);
    }
    
    /**
     * Busca un Docente por su nrp y devuelve su posicion
     * @param nrp el nrp a buscar
     * @return la posicion del docente, o -1 si no se encuentra
     */
    public int buscarDocente(String nrp){
        int toRet = -1;
        int i = 0;
        int num = getNumDocentes();
        while (toRet == -1 && i <  num) {
            if (nrp.equalsIgnoreCase(getDocente(i).getNRP())) {
                toRet = i;
            }
            i++;
        }
        return toRet;
    
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
     * @throws IndexOutOfBoundsException si la posicion no es valida
     */
    public Estudiante getEstudiante(int pos) {
       return estudiantes.get(pos);
    }

    /**
     * Inserta un nuevo estudiante.
     *
     * @param a el nuevo objeto Estudiante
     */
    public void insertaEstudiante(Estudiante a) throws MatriculaInvalidaException {
        if (buscarEstudiante(a.getNumMatricula()) != -1) {
            throw new MatriculaInvalidaException("No se puede insertar un estudiante"
                    + "con un numero de matricula repetida");
        }
        estudiantes.add(a);
    }

    /**
     * Elimina el estudiante que se encuentra en la posición indicada.
     *
     * @param pos la posición del estudiante a eliminar
     * @throws es.uvigo.esei.proii.core.NoEliminableException
     * @throws IndexOutOfBoundsException si la posicion no es valida
     */
    public void eliminaEstudiante(int pos) throws NoEliminableException {
        if (buscarTutoria(getEstudiante(pos)) != -1) {
            throw new NoEliminableException("El estudiante tiene tutoria");
        }
        estudiantes.remove(pos);
    }

    /**
     * Busca un Estudiante por su numero de Matricula y devuelve su posicion
     * @param numMatricula  el numero de Matricula a buscar
     * @return la posicion del estudiante, o -1 si no se encuentra
     */
    public int buscarEstudiante(String numMatricula){
        
        int toRet = -1;
        int i = 0;
        int num = getNumEstudiantes();
        while (toRet == -1 && i <  num) {
            if (numMatricula.equals(getEstudiante(i).getNumMatricula())) {
                toRet = i;
            }
            i++;
        }
        return toRet;
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
     * Devuelve la tutoria situada en pos
     *
     * @param pos el lugar de la tutoria en el vector de tutorias
     * @return el objeto Tutoria correspondiente.
     * @throws IndexOutOfBoundsException si la posicion es invalida
     */
    public Tutoria getTutoria(int pos)  {
        return this.tutorias.get(pos); 
    }

    /**
     * Inserta una nueva tutoria.
     *
     * @param p el nuevo objeto tutoria
     */
    public void insertaTutoria(Tutoria p) {
        tutorias.add(p);
    }

    /**
     * Elimina la tutoria que se encuentra en la posición indicada.
     *
     * @param pos la posición de la tutoria a eliminar
     * @throws IndexOutOfBoundsException si la posicion es invalida
     */
    public void eliminaTutoria(int pos) {
        //Lanza IndexOutOfBoundsException por si solo
        tutorias.remove(pos);
    }
    
    /**
     * Busca una tutoria a partir de su Estudiante
     * @param e el estudiante
     * @return la posicion de la tutoria, o -1 si no se encuentra
     */
    public int buscarTutoria(Estudiante e){
        int toRet = -1;
        int i = 0;
        int num = getNumTutorias();
        while (toRet != -1 && i <  num) {
            if (e.equals(getTutoria(i).getEstudiante())) {
               toRet = i;
            }
            i++;
        }
        return toRet;
    }
    
    /**
     * Busca una tutoria a partir de su Profesor
     * @param e el profesor
     * @return la posicion de la tutoria, o -1 si no se encuentra
     */
    public int buscarTutoria(Docente e){
        int toRet = -1;
        int i = 0;
        int num = getNumTutorias();
        while (toRet != -1 && i <  num) {
            if (e.equals(getTutoria(i).getProfesor())) {
               toRet = i;
            }
            i++;
        }
        return toRet;
    }
    
  
}
