package es.uvigo.esei.proii.entidades;

import nu.xom.Element;
import nu.xom.ParsingException;

/**
 *
 * @author Nanny
 */
public class Estudiante extends Persona{

    private String numeroMatricula;
    private static final String TAG_ESTUDIANTE = "Estudiante";
    private static final String TAG_NUMMATRICULA = "numMatricula";


    public Estudiante(String numeroMatricula, int dni, String nombre) {
        super(dni, nombre);
        this.numeroMatricula = numeroMatricula;
    }

    public Estudiante(Element e) throws ParsingException{
        super(e);
        Element eNM = e.getFirstChildElement(TAG_NUMMATRICULA);
        if (eNM == null) {
            throw new ParsingException("no se ha encontrado la etiqueta NumMatricula");
        }
        setNumeroMatricula(eNM.getValue().trim());
    }
    
    
    public Element toDOM(){
        Element raiz = super.toDOM();
        raiz.setLocalName(TAG_ESTUDIANTE);
        
        Element eNM = new Element(TAG_NUMMATRICULA);
        eNM.appendChild(numeroMatricula);
        raiz.appendChild(eNM);
        
        return raiz;
    }

    /**
     * Comprueba si 2 Estudiantes son iguales, es decir, si son la misma Persona y tienen la misma matricula
     * @param e
     * @return 
     */
    public boolean equals(Estudiante e){
        return super.equals(e) && this.numeroMatricula.equalsIgnoreCase(e.getNumeroMatricula());
        
    }
    
    /**
     * Devuelve el valor de la etiqueta numMatricula
     *
     * @return el numMatricula
     */
    public String getNumeroMatricula() {
        return numeroMatricula;
    }

     /**
     * Cambia el numMatricula
     *
     * @param numMatricula El nuevo valor, como String
     */
    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();

        toret.append("Estudiante. ");
        toret.append(super.toString());
        toret.append("\tNumero Matricula: ")
                .append(getNumeroMatricula());
       
        return toret.toString();
    }
}
