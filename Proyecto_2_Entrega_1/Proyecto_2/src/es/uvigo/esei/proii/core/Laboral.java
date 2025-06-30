/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uvigo.esei.proii.core;

import nu.xom.Element;
import nu.xom.ParsingException;

/**
 *
 * @author dfabi
 */
public class Laboral extends Docente {
    Fecha fechaInicio;
    Fecha fechaFin;

    public  final static String TAG_LABORAL = "Laboral";
    private final static String TAG_FINICIO = "Fecha_Inicio";
    private final static String TAG_FFIN = "Fecha_Fin";
    
    public Laboral(Fecha fechaInicio, Fecha fechaFin, int despacho, String nrp, String nombre, int dni) throws FechaInvalidaException {
        super(despacho, nrp, nombre, dni);
        this.setFechaInicio(fechaInicio);
        this.setFechaFin(fechaFin);
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
    
    
    
    
    /**
     * Compara 2 laborables, es decir, si son el mismo docente y tienen las mismas fechas
     * @param l
     * @return 
     */
    public boolean equals(Laboral l){
        return super.equals(l) && l.getFechaInicio().equals(fechaInicio) && l.getFechaFin().equals(fechaFin);
    }
    
    public Fecha getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Fecha fechaInicio) throws FechaInvalidaException {
        if(fechaFin != null && fechaInicio.compareTo(fechaFin) > 0){
            throw new FechaInvalidaException("La fecha de inicio no puede ser posterior a la fecha final");
        }
        this.fechaInicio = fechaInicio;
    }

    public Fecha getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Fecha fechaFin) throws FechaInvalidaException {
        if(fechaInicio != null && fechaInicio.compareTo(fechaFin) > 0){
            throw new FechaInvalidaException("La fecha de inicio no puede ser posterior a la fecha final");
        }
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Laboral.\n");
        sb.append(super.toString());
        sb.append("\tfechaInicio: ").append(getFechaInicio().toString());
        sb.append("\tfechaFin: ").append(getFechaFin().toString());
        return sb.toString();
    }
    
    
    
    
}
