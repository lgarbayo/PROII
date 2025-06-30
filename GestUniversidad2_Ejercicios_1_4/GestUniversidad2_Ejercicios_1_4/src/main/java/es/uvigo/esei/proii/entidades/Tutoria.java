
package es.uvigo.esei.proii.entidades;

import nu.xom.Element;
import nu.xom.ParsingException;

/**
 *
 * @author nrufino
 */
public class Tutoria {
    private Docente profesor;
    private Estudiante estudiante;
    private Fecha fechaTutoria;
    private Hora horaTutoria;
    private static final String TAG_TUTORIA = "Tutoria";
    private static final String TAG_PROFESOR = "Profesor";
    private static final String TAG_ESTUDIANTE = "Estudiante";
    private static final String TAG_FECHA = "FechaTutoria";
    private static final String TAG_HORA = "HoraTutoria";

    public Tutoria(Docente profesor, Estudiante estudiante, 
            Fecha fechaTutoria, Hora horaTutoria) {
        this.profesor = profesor;
        this.estudiante = estudiante;
        this.fechaTutoria = fechaTutoria;
        this.horaTutoria = horaTutoria;
    }
    
    public Tutoria(Element e) throws ParsingException{
        Element eProfesor = e.getFirstChildElement(TAG_PROFESOR);
        Element eEstudiante = e.getFirstChildElement(TAG_ESTUDIANTE);
        Element eHora = e.getFirstChildElement(TAG_HORA);
        Element eFecha = e.getFirstChildElement(TAG_FECHA);
    
        if(eProfesor == null){
            throw new ParsingException("No se ha encontrado la etiqueta "+TAG_PROFESOR);
        }
        if(eEstudiante == null){
            throw new ParsingException("No se ha encontrado la etiqueta "+TAG_ESTUDIANTE);
        }
        if(eHora == null){
            throw new ParsingException("No se ha encontrado la etiqueta "+TAG_HORA);
        }
        if(eFecha == null){
            throw new ParsingException("No se ha encontrado la etiqueta "+TAG_FECHA);
        }
        setEstudiante(new Estudiante(eEstudiante));
        Docente miProfesor = null;
        if (eProfesor.getLocalName().equals(Laboral.TAG_LABORAL)) {
            miProfesor = new Laboral(eProfesor);
        }
        if (eProfesor.getLocalName().equals(Funcionario.TAG_FUNCIONARIO)) {
            miProfesor = new Funcionario(eProfesor);
        }
        if(eProfesor == null){
            throw new ParsingException("Error al leer un docente");
        }
        setProfesor(miProfesor);
        setHoraTutoria(new Hora(eHora));
        setFechaTutoria(new Fecha(eFecha));
    }

    public Element toDOM(){
        Element raiz = new Element(TAG_TUTORIA);
        
        Element eProfesor = profesor.toDOM();
        eProfesor.setLocalName(TAG_PROFESOR);
        raiz.appendChild(eProfesor);
        
        Element eEstudiante = estudiante.toDOM();
        eEstudiante.setLocalName(TAG_ESTUDIANTE);
        raiz.appendChild(eEstudiante);
        
        Element eFecha = fechaTutoria.toDOM();
        eFecha.setLocalName(TAG_FECHA);
        raiz.appendChild(eFecha);
        
        Element eHora = horaTutoria.toDOM();
        eHora.setLocalName(TAG_HORA);
        raiz.appendChild(eHora);
    
        return raiz;
    }

    public Docente getProfesor() {
        return profesor;
    }

    public void setProfesor(Docente profesor) {
        this.profesor = profesor;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Fecha getFechaTutoria() {
        return fechaTutoria;
    }

    public void setFechaTutoria(Fecha fechaTutoria) {
        this.fechaTutoria = fechaTutoria;
    }

    public Hora getHoraTutoria() {
        return horaTutoria;
    }

    public void setHoraTutoria(Hora horaTutoria) {
        this.horaTutoria = horaTutoria;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();
        
        toret.append("\nTutoria");
        toret.append("\nProfesor: ").append(getProfesor());
        toret.append("\nEstudiante: ").append(getEstudiante());
        toret.append("\nFecha Tutoria: ").append(getFechaTutoria());
        toret.append("\tHora Tutoria: ").append(getHoraTutoria());

        return toret.toString();
    }
    
    
}
