/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.practicobloque2_1;

import java.io.IOException;

/**
 *
 * @author Luis
 */
public class PracticoBloque2_1 {

    public static void main(String[] args) {
        System.out.println("45145426Z, Luis Garbayo Fernández, PROII_3, código");
        
        SedeCatastro sede = new SedeCatastro(222, "www.sede.com");
        Catastro cata = new Catastro(sede);
        
        Piso p1 = new Piso(2, "B", "nosequeesesto", 56);
        Piso p2 = new Piso(1, "A", "nosequeesesto", 86);
        
        Casa c1 = new Casa(10, TipoSuelo.MIXTO, "nosequeesesto", 125);
        Casa c2 = new Casa(3, TipoSuelo.URBANO, "nosequeesesto", 3);
        
        Adosado a1 = new Adosado("madrid por desgracia", 15, "nosequeesesto", 57);
        Adosado a2 = new Adosado("ourense", 39, "nosequeesesto", 79);
        
        cata.insertarVivienda(p1);
        cata.insertarVivienda(p2);
        cata.insertarVivienda(c1);
        cata.insertarVivienda(c2);
        cata.insertarVivienda(a1);
        cata.insertarVivienda(a2);
        
        try {
            cata.toXML("catastro.xml");
        } catch (IOException ex) {
            System.err.println("Ha habido un error al crear el fichero XML");
        }
    }
}
