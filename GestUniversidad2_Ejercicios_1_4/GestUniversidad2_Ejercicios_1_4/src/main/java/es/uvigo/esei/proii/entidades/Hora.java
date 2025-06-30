package es.uvigo.esei.proii.entidades;

import nu.xom.Element;
import nu.xom.ParsingException;

/**
 *
 * @author Nanny
 */
public class Hora {

    public static final int NUMHORAS = 23;
    public static final int NUMMINUTOS = 59;

    private int hora;
    private int minutos;
    
    private final static String TAG_HORA = "Hora";
    private final static String TAG_HORA_AT = "hora";
    private final static String TAG_MIN = "Min";

    public Hora(int hora, int minutos) {
        this.hora = hora;
        this.minutos = minutos;
    }

    public Hora() {
        this.hora = 0;
        this.minutos = 0;
    }

    public Hora(Element e) throws ParsingException{
        Element eHora = e.getFirstChildElement(TAG_HORA_AT);
        Element eMin = e.getFirstChildElement(TAG_MIN);
        if (eHora == null) {
            throw new ParsingException("Falta la etiqueta Hora");
        }
        if (eMin == null) {
            throw new ParsingException("Falta la etiqueta Min");
        }
        
        
        try {
            setMinutos(Integer.parseInt(eMin.getValue().trim()));
        } catch (NumberFormatException ex) {
            throw new ParsingException("Error al leer la etiqueta min");
        }
        try {
            setHora(Integer.parseInt(eHora.getValue().trim()));
        } catch (NumberFormatException ex) {
            throw new ParsingException("Error al leer la etiqueta hora");
        }
    }
    
    public Element toDOM(){
        Element raizHora = new Element(TAG_HORA);

        Element eHora = new Element(TAG_HORA_AT);
        eHora.appendChild(Integer.toString(hora));
        raizHora.appendChild(eHora);
        
        Element eMin = new Element(TAG_MIN);
        eMin.appendChild(Integer.toString(minutos));
        raizHora.appendChild(eMin);
        
        return raizHora;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();

        toret.append(getHora()).append(":").append(getMinutos());

        return toret.toString();
    }

}
