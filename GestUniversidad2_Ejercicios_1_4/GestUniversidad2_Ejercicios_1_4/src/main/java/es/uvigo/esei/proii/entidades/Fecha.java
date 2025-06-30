package es.uvigo.esei.proii.entidades;

import nu.xom.Element;
import nu.xom.ParsingException;

/**
 *
 * @author Nanny
 */
public class Fecha {

    public static final int MAXDIA = 31;
    public static final int MAXMES = 12;
    public static final int CARANHO = 4;

    private int dia;
    private int mes;
    private int anho;
    
    private static final String TAG_FECHA = "Fecha";
    private static final String TAG_MES = "Mes";
    private static final String TAG_DIA = "Dia";
    private static final String TAG_ANO = "Ano";

    public Fecha(int dia, int mes, int anho) {
        this.dia = dia;
        this.mes = mes;
        this.anho = anho;
    }

    public Fecha() {
        this.dia = 0;
        this.mes = 0;
        this.anho = 0;
    }

    public Fecha(Element e) throws ParsingException{
        Element eDia = e.getFirstChildElement(TAG_DIA);
        Element eMes = e.getFirstChildElement(TAG_MES);
        Element eAno = e.getFirstChildElement(TAG_ANO);
        if (eDia == null) {
            throw new ParsingException("Falta la etiqueta Dia");
        }
        if (eMes == null) {
            throw new ParsingException("Falta la etiqueta Mes");
        }
        if (eAno == null) {
            throw new ParsingException("Falta la etiqueta Ano");
        }
        try {
            setDia(Integer.parseInt(eDia.getValue().trim()));
        } catch (NumberFormatException ex) {
            throw new ParsingException("Error al leer la etiqueta dia");
        }
        try {
            setMes(Integer.parseInt(eMes.getValue().trim()));
        } catch (NumberFormatException ex) {
            throw new ParsingException("Error al leer la etiqueta mes");
        }
        try {
            setAnho(Integer.parseInt(eAno.getValue().trim()));
        } catch (NumberFormatException ex) {
            throw new ParsingException("Error al leer la etiqueta ano");
        }
    }
    
    public Element toDOM(){
        Element raizFecha = new Element(TAG_FECHA);
        
        Element eDia = new Element(TAG_DIA);
        eDia.appendChild(Integer.toString(getDia()));
        raizFecha.appendChild(eDia);
        
        Element eMes = new Element(TAG_MES);
        eMes.appendChild(Integer.toString(getMes()));
        raizFecha.appendChild(eMes);
        
        Element eAno = new Element(TAG_ANO);
        eAno.appendChild(Integer.toString(getAnho()));
        raizFecha.appendChild(eAno);
        
        return raizFecha;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnho() {
        return anho;
    }

    public void setAnho(int anho) {
        this.anho = anho;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();

        toret.append(getDia()).append("/");
        toret.append(getMes()).append("/");
        toret.append(getAnho());

        return toret.toString();
    }

}
