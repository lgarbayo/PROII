package es.uvigo.esei.proii.core;

import nu.xom.Element;
import nu.xom.ParsingException;

public class Fecha implements Comparable<Fecha>{
    private int dia;
    private int mes;
    private int ano;
    
    private static final String TAG_FECHA = "Fecha";
    private static final String TAG_MES = "Mes";
    private static final String TAG_DIA = "Dia";
    private static final String TAG_ANO = "Ano";
    
    public Fecha(int dia, int mes,int ano) throws FechaException{
        this.setDia(dia);
        this.setMes(mes);
        this.setAno(ano);
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
            setAno(Integer.parseInt(eAno.getValue().trim()));
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
        eAno.appendChild(Integer.toString(getAno()));
        raizFecha.appendChild(eAno);
        
        return raizFecha;
    }
    
    public Fecha(Fecha f) throws FechaException{
        this(f.getDia(),f.getMes(),f.getAno());
    }
    public int getDia() {
        return dia;
    }

    public void setDia(int dia) throws FechaException{
        if(dia < 1 || dia > 31){
            throw new FechaException("El dia debe estar entre 1 y 31");
        }
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) throws FechaException{
        if(mes < 1 || mes > 12){
            throw new FechaException("El mes debe estar entre 1 y 12");
        }
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%02d", dia)).append("/");
        sb.append(String.format("%02d", mes)).append("/");
        sb.append(String.format("%04d", ano));
        return sb.toString();
    }   

    public boolean equals(Fecha f){
        return this.compareTo(f) == 0;
    }
    
    @Override
    /**
     * Retorna un entero negativo si this es menor, 0 si son iguales, positivo si this es mayor
     * 
     */
    public int compareTo(Fecha o) {
        int toRet;
        if (this.ano != o.ano) {
            toRet = this.ano - o.ano;
        } else {
            if (this.mes != o.mes) {
                toRet = this.mes - o.mes;
            } else {
                toRet = this.dia - o.dia;
            }
        }
        return toRet;
    }

}
