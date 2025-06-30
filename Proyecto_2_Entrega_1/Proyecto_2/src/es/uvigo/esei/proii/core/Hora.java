package es.uvigo.esei.proii.core;

import nu.xom.Element;
import nu.xom.ParsingException;

public class Hora {
    private int hora;
    private int min;

    private final static String TAG_HORA = "Hora";
    private final static String TAG_HORA_AT = "hora";
    private final static String TAG_MIN = "Min";
    
    public Hora(int hora, int min) throws HoraException{
        this.setHora(hora);
        this.setMin(min);
    }

    public Hora(Hora hora) throws HoraException {
        this(hora.getHora(),hora.getMin());
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
            setMin(Integer.parseInt(eMin.getValue().trim()));
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
        eMin.appendChild(Integer.toString(min));
        raizHora.appendChild(eMin);
        
        return raizHora;
    }
    
    
    public int getHora() {
        return hora;
    }

    public void setHora(int hora) throws HoraException{
        if (hora<0 || hora > 23) {
            throw new HoraException("una hora esta entre las 00 y las 23");
        }
        this.hora = hora;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) throws HoraException{
        if (min<0 || min > 59) {
            throw new HoraException("un minuto esta entre 0 y 59");
        }
        this.min = min;
    }
 
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%02d", this.getHora())).append(":");
        sb.append(String.format("%02d", this.getMin()));
        return sb.toString();
    }
    
}