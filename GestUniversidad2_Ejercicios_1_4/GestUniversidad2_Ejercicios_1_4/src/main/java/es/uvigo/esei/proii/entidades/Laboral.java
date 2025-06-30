
package es.uvigo.esei.proii.entidades;

import nu.xom.Element;
import nu.xom.ParsingException;

/**
 *
 * @author Nanny
 */
public class Laboral extends Docente {
    private Fecha fechaInicio;
    private Fecha fechaFin;
    public  final static String TAG_LABORAL = "Laboral";
    private final static String TAG_FINICIO = "Fecha_Inicio";
    private final static String TAG_FFIN = "Fecha_Fin";

    public Laboral(Fecha fechaInicio, Fecha fechaFin, 
            int dni, String nombre, 
            String nrp, int despacho) {
        super(dni, nombre, nrp, despacho);
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    
    public Laboral(Element e) throws ParsingException{
        super(e);
        Element eFI = e.getFirstChildElement(TAG_FINICIO);
        Element eFF = e.getFirstChildElement(TAG_FFIN);
        if(eFI == null){
            throw new ParsingException("No se ha encontrado la etiqueta "+TAG_FINICIO);
        }
        if (eFF == null) {
            throw new ParsingException("No se ha encontrado la etiqueta "+TAG_FFIN);
        }
        setFechaInicio(new Fecha(eFI));
        setFechaFin(new Fecha(eFF));
    }
    
    @Override
    public Element toDOM(){
        Element raiz = super.toDOM();
        raiz.setLocalName(TAG_LABORAL);
        
        Element eFI = fechaInicio.toDOM();
        eFI.setLocalName(TAG_FINICIO);
        raiz.appendChild(eFI);
        
        Element eFF = fechaFin.toDOM();
        eFF.setLocalName(TAG_FFIN);
        raiz.appendChild(eFF);
        
        return raiz;
    }

    public Fecha getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Fecha fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Fecha getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Fecha fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();
        
        toret.append("Laboral Docente.");
        toret.append(super.toString());
        toret.append("\tFecha Inicio: ").append(getFechaInicio());
        toret.append("\tFecha Fin: ").append(getFechaFin());

        return toret.toString();
    }
    
    
    
}
