package es.uvigo.esei.proii.core;

import nu.xom.Element;
import nu.xom.ParsingException;

public abstract class Docente extends Persona{

    
    private int despacho;
    private String nrp;

    private static final String TAG_DOCENTE = "Docente";
    private static final String TAG_DESPACHO = "Despacho";
    private static final String TAG_NRP = "NRP";
    
    public Docente(int despacho, String nrp, String nombre, int dni) {
        super(nombre, dni);
        this.setDespacho(despacho);
        this.setNRP(nrp);
    }

    public Docente(Element e) throws ParsingException{
        super(e);
        Element eNRP = e.getFirstChildElement(TAG_NRP);
        Element eDespacho = e.getFirstChildElement(TAG_DESPACHO);
        
        if (eNRP == null) {
            throw new ParsingException("No se ha encontrado la etiqueta NRP");
        }
        if (eDespacho == null) {
            throw new ParsingException("No se ha encontrado la etiqueta Despacho");
        }
        setNRP(eNRP.getValue().trim());
        try {
            setDespacho(Integer.parseInt(eDespacho.getValue().trim()));
        } catch (NumberFormatException ex) {
            throw new ParsingException("No se ha leido la etiqueta Despacho");
        }
        
    }
    @Override
    public Element toDOM(){
        Element raiz = super.toDOM();
        raiz.setLocalName(TAG_DOCENTE);
        
        Element eNRP = new Element(TAG_NRP);
        eNRP.appendChild(nrp);
        raiz.appendChild(eNRP);
        
        Element eDespacho = new Element(TAG_DESPACHO);
        eDespacho.appendChild(Integer.toString(despacho));
        raiz.appendChild(eDespacho);
        
        return raiz;
    }
    
    /**
     * Comprueba si 2 docentes son iguales, es decir, si son la misma persona, y tienen mismo despacho y nrp
     * 
     * @param d
     * @return 
     */
    public boolean equals(Docente d){
        return super.equals(d) && this.despacho == d.getDespacho() && this.nrp.equalsIgnoreCase(d.getNRP());
    }
            
    /**
     * Devuelve el despacho del docente
     *
     * @return el despacho del docente, como int.
     */
    public int getDespacho() {
        return despacho;
    }

    /**
     * Cambia el despacho del docente
     *
     * @param despacho el despacho del docente
     */
    public void setDespacho(int despacho) {
        this.despacho = despacho;
    }

    /**
     * Devuelve el valor del NRP 
     *
     * @return el NRP, como String
     */
    public String getNRP() {
        return this.nrp;
    }

    /**
     * Cambia el NRP del docente
     *
     * @param nrp El nuevo valor, como String
     */
    public void setNRP(String nrp) {
        this.nrp = nrp;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();

        toret.append(super.toString());
        toret.append("\tDespacho: ").append(getDespacho());
        toret.append("\tNRP: ").append(this.getNRP());
                

        return toret.toString();
    }
}
