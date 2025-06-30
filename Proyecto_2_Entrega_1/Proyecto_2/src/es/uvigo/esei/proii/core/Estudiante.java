package es.uvigo.esei.proii.core;

import nu.xom.Element;
import nu.xom.ParsingException;


public class Estudiante extends Persona{

    private String numMatricula;

    private static final String TAG_ESTUDIANTE = "Estudiante";
    private static final String TAG_NUMMATRICULA = "numMatricula";
    
    public Estudiante(String numMatricula, String nombre, int dni) {
        super(nombre, dni);
        this.setNumMatricula(numMatricula);
    }

    public Estudiante(Element e) throws ParsingException{
        super(e);
        Element eNM = e.getFirstChildElement(TAG_NUMMATRICULA);
        if (eNM == null) {
            throw new ParsingException("no se ha encontrado la etiqueta NumMatricula");
        }
        setNumMatricula(eNM.getValue().trim());
    }
    
    
    @Override
    public Element toDOM(){
        Element raiz = super.toDOM();
        raiz.setLocalName(TAG_ESTUDIANTE);
        
        Element eNM = new Element(TAG_NUMMATRICULA);
        eNM.appendChild(numMatricula);
        raiz.appendChild(eNM);
        
        return raiz;
    }
    
    /**
     * Comprueba si 2 Estudiantes son iguales, es decir, si son la misma Persona y tienen la misma matricula
     * @param e
     * @return 
     */
    public boolean equals(Estudiante e){
        return super.equals(e) && this.numMatricula.equalsIgnoreCase(e.getNumMatricula());
        
    }
    
    /**
     * Devuelve el valor de la etiqueta numMatricula
     *
     * @return el numMatricula
     */
    public String getNumMatricula() {
        return this.numMatricula;
    }

    /**
     * Cambia el numMatricula
     *
     * @param numMatricula El nuevo valor, como String
     */
    public void setNumMatricula(String numMatricula) {
        this.numMatricula = numMatricula;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();

        toret.append("Estudiante.\n");
        toret.append(super.toString());
        toret.append("\tNum Matricula: ").append(getNumMatricula());

        return toret.toString();
    }
}