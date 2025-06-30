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
public abstract class Vivienda {
    private String referenciaCastral;
    protected int metrosCuadrados;

    public Vivienda(String referenciaCastral, int metrosCuadrados) {
        this.referenciaCastral = referenciaCastral;
        this.metrosCuadrados = metrosCuadrados;
    }

    public String getReferenciaCastral() {
        return referenciaCastral;
    }

    public int getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setReferenciaCastral(String referenciaCastral) {
        this.referenciaCastral = referenciaCastral;
    }

    public void setMetrosCuadrados(int metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }
    
    public Element vivienda_toDOM() {
        Element raiz = new Element("vivienda");
        Element el_referenciaCatastral = new Element("referenciaCatastral");
        Element el_metrosCuadrados = new Element("metrosCuadrados");

        el_referenciaCatastral.appendChild(this.referenciaCastral);
        el_metrosCuadrados.appendChild(Integer.toString(this.metrosCuadrados));

        raiz.appendChild(el_referenciaCatastral);
        raiz.appendChild(el_metrosCuadrados);

        return raiz;

    }
    public abstract Element toDOM();
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vivienda{");
        sb.append("referenciaCastral=").append(referenciaCastral);
        sb.append(", metrosCuadrados=").append(metrosCuadrados);
        sb.append('}');
        return sb.toString();
    }
    
    public abstract double calcularImpuestoBienesInmuebles ();
}
