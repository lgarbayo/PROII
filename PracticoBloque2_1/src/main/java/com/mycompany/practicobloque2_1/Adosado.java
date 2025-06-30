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
public class Adosado extends Vivienda {
    private String urbanizacion;
    private int numero;

    public Adosado(String urbanizacion, int numero, String referenciaCastral, int metrosCuadrados) {
        super(referenciaCastral, metrosCuadrados);
        this.urbanizacion = urbanizacion;
        this.numero = numero;
    }

    public String getUrbanizacion() {
        return urbanizacion;
    }

    public int getNumero() {
        return numero;
    }

    public void setUrbanizacion(String urbanizacion) {
        this.urbanizacion = urbanizacion;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Adosado{");
        sb.append("urbanizacion=").append(urbanizacion);
        sb.append(", numero=").append(numero);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public double calcularImpuestoBienesInmuebles() {
        return 50;
    }
    
    @Override
    public Element toDOM() {
        Element raiz = super.vivienda_toDOM();
        Element tipoVivienda = new Element("tipoVivienda");
        tipoVivienda.appendChild("adosado");
        raiz.appendChild(tipoVivienda);
        
        Element el_urbanizacion = new Element("urbanizacion");
        Element el_numero = new Element("numero");
        
        el_numero.appendChild(this.urbanizacion);        
        el_urbanizacion.appendChild(Integer.toString(this.numero));

        raiz.appendChild(el_numero);
        raiz.appendChild(el_urbanizacion);
        
        return raiz;
    }
    
}
