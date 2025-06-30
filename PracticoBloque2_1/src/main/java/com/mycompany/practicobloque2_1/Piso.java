/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicobloque2_1;

import nu.xom.Element;

/**
 *
 * @author Luis
 */
public class Piso extends Vivienda {
    private int piso;
    private String puerta;

    public Piso(int piso, String puerta, String referenciaCastral, int metrosCuadrados) {
        super(referenciaCastral, metrosCuadrados);
        this.piso = piso;
        this.puerta = puerta;
    }
    
    @Override
    public double calcularImpuestoBienesInmuebles () {
        double base = this.metrosCuadrados * 0.15;
        
        if(this.metrosCuadrados < 75){
            return base + 15;
        }else if(this.metrosCuadrados < 105){
            return base + 20;
        }else{
            return base + 35;
        }
    }

    public Element toDOM() {
        Element raiz = super.vivienda_toDOM();
        Element tipoVivienda = new Element("tipoVivienda");
        tipoVivienda.appendChild("piso");
        raiz.appendChild(tipoVivienda);
        
        Element el_puerta = new Element("puerta");
        Element el_piso = new Element("piso");
        
        el_puerta.appendChild(this.puerta);        
        el_piso.appendChild(Integer.toString(this.piso));
        
        raiz.appendChild(el_puerta);
        raiz.appendChild(el_piso);
        
        return raiz;
    }
    
    public int getPiso() {
        return piso;
    }

    public String getPuerta() {
        return puerta;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Piso{");
        sb.append("piso=").append(piso);
        sb.append(", puerta=").append(puerta);
        sb.append('}');
        return sb.toString();
    }
    
}
