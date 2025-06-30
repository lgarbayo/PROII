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
public class Casa extends Vivienda {
    private int alturas;
    private TipoSuelo tipoSuelo;

    public Casa(int alturas, TipoSuelo tipoSuelo, String referenciaCatastral, int metrosCuadrados) {
        super(referenciaCatastral, metrosCuadrados);
        this.alturas = alturas;
        this.tipoSuelo = tipoSuelo;
    }

    public int getAlturas() {
        return alturas;
    }

    public TipoSuelo getTipoSuelo() {
        return tipoSuelo;
    }

    public void setAlturas(int alturas) {
        this.alturas = alturas;
    }

    public void setTipoSuelo(TipoSuelo tipoSuelo) {
        this.tipoSuelo = tipoSuelo;
    }

    @Override
    public Element toDOM() {
        Element raiz = super.vivienda_toDOM();
        Element tipoVivienda = new Element("tipoVivienda");
        tipoVivienda.appendChild("casa");
        raiz.appendChild(tipoVivienda);
        
        Element el_alturas = new Element("alturas");
        Element el_tipoSuelo = new Element("tipoSuelo");
        
        el_alturas.appendChild(Integer.toString(this.alturas));
        el_tipoSuelo.appendChild(this.tipoSuelo.name());
        raiz.appendChild(el_alturas);
        raiz.appendChild(el_tipoSuelo);
        
        return raiz;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Casa{");
        sb.append("alturas=").append(alturas);
        sb.append(", tipoSuelo=").append(tipoSuelo);
        sb.append('}');
        return sb.toString();
    }
    
    @Override
    public double calcularImpuestoBienesInmuebles() {
        return this.tipoSuelo.ibi;
    }
}
