
package es.uvigo.esei.proii.entidades;

import nu.xom.Element;
import nu.xom.ParsingException;

/**
 *
 * @author Nanny
 */
public class Funcionario extends Docente {
    private Fecha fechaPosesion;
    public  static final String TAG_FUNCIONARIO = "Funcionario";
    private static final String TAG_FECHA_POSESION = "fechaDePosesion";

    public Funcionario(Fecha fechaPosesion, int dni, String nombre, 
            String nrp, int despacho) {
        super(dni, nombre, nrp, despacho);
        this.fechaPosesion = fechaPosesion;
    }
    
    public Funcionario(Element e) throws ParsingException{
        super(e);
        Element eFecha = e.getFirstChildElement(TAG_FECHA_POSESION);
        if (eFecha == null) {
            throw new ParsingException("No se ha encontrado la etiqueta Toma posesion");
        }
        setFechaPosesion(new Fecha(eFecha));
    }
    
    @Override
    public Element toDOM(){
        Element raiz = super.toDOM();
        raiz.setLocalName(TAG_FUNCIONARIO);
        
        Element eTP = fechaPosesion.toDOM();
        eTP.setLocalName(TAG_FECHA_POSESION);
        raiz.appendChild(eTP);
        
        return raiz;
    }

    public Fecha getFechaPosesion() {
        return fechaPosesion;
    }

    public void setFechaPosesion(Fecha fechaPosesion) {
        this.fechaPosesion = fechaPosesion;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();
        
        toret.append("Docente Funcionario.");
        toret.append(super.toString());
        toret.append("\tFecha de Posesion: ").append(getFechaPosesion());

        return toret.toString();
    }    
     
}
