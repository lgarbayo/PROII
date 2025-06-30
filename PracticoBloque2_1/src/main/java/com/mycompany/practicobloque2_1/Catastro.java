/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicobloque2_1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

/**
 *
 * @author Luis
 */
public class Catastro {
    private List<Vivienda> viviendas;
    private SedeCatastro sede;

    public Catastro(SedeCatastro sede) {
        this.viviendas = new ArrayList<>();
        this.sede = sede;
    }

    public List<Vivienda> getViviendas() {
        return viviendas;
    }
    
    public SedeCatastro getSede() {
        return sede;
    }

    public void setViviendas(List<Vivienda> viviendas) {
        this.viviendas = viviendas;
    }
    
    public void setSede(SedeCatastro sede) {
        this.sede = sede;
    }
    
    public Element toDOM(){
        Element raiz = new Element("catastro");
        Element el_sede = this.sede.toDOM();
        raiz.appendChild(el_sede);
        
        Element el_viviendas = new Element("viviendas");
        for (Vivienda vivienda : viviendas) {
            el_viviendas.appendChild(vivienda.toDOM());
        }
        
        raiz.appendChild(el_viviendas);
        
        return raiz;
    
    }
    
    public void toXML(String filename) throws IOException{
        FileOutputStream f = new FileOutputStream(filename);
        Serializer serial = new Serializer(f);
        Document doc = new Document(this.toDOM());
        serial.write(doc);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Catastro{");
        sb.append("viviendas=").append(viviendas);
        sb.append('}');
        return sb.toString();
    }
    
    public void insertarVivienda (Vivienda v) {
        this.viviendas.add(v);
    }
}
