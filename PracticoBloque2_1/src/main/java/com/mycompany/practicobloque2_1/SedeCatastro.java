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
public class SedeCatastro {
    private int codigoNormativaVigente;
    private String paginaWeb;

    public SedeCatastro(int codigoNormativaVigente, String paginaWeb) {
        this.codigoNormativaVigente = codigoNormativaVigente;
        this.paginaWeb = paginaWeb;
    }

    public int getCodigoNormativaVigente() {
        return codigoNormativaVigente;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setCodigoNormativaVigente(int codigoNormativaVigente) {
        this.codigoNormativaVigente = codigoNormativaVigente;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public Element toDOM(){
        Element raiz = new Element("raiz");
        Element el_codigo = new Element("codigoNormativaVigente");
        Element el_paginaWeb = new Element("paginaWeb");
        
        el_codigo.appendChild(Integer.toString(this.codigoNormativaVigente));
        el_paginaWeb.appendChild(this.paginaWeb);
        
        raiz.appendChild(el_codigo);
        raiz.appendChild(el_paginaWeb);
        
        return raiz;
    }
    
    @Override
    public String toString() {
        return "SedeCatastro{" + "codigoNormativaVigente=" + codigoNormativaVigente + ", paginaWeb=" + paginaWeb + '}';
    }
    
    
}
