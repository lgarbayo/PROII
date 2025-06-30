/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uvigo.esei.proii.iu;

import es.uvigo.esei.proii.entidades.Persona;

/**
 *
 * @author Nanny
 */
public class IlcGestionPersona {

    private Entrada entrada;

    public IlcGestionPersona(Entrada entrada) {
        this.entrada = entrada;
    }

    public void modificaPersona(Persona p) {
        String info;
        boolean vacio = false;

        // D.N.I.
        System.out.print("\tD.N.I.");
        if (p.getDni() > 0) {
            System.out.print(" [" + p.getDni() + "]");
            vacio = true;
        }
        info = entrada.leeCadena(": ", vacio);
        if (info.length() > 0) {
            p.setDni(Integer.parseInt(info));
        }

        // Nombre
        System.out.print("\tNombre");
        if (p.getNombre().length() > 0) {
            System.out.print(" [" + p.getNombre() + "]");
            vacio = true;
        }
        info = entrada.leeCadena(": ", vacio);
        if (info.length() > 0) {
            p.setNombre(info);
        }

    }
    
}
