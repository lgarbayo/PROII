/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uvigo.esei.proii.iu;

import es.uvigo.esei.proii.entidades.Universidad;
import es.uvigo.esei.proii.entidades.Fecha;
import es.uvigo.esei.proii.entidades.Hora;

/**
 *
 * @author ncamb
 */
public class IlcGestionFechaHora {
    
    private Entrada entrada;

    public IlcGestionFechaHora(
            Entrada entrada) {
       
        this.entrada = entrada;
    }
    
    /**
     * *** FECHA ***
     */
    /**
     * Modifica/Lee los datos de una fecha (puede ser fecha de toma de posesión,
     * o la fecha de inicio y de fin de un docente laboral).
     */
    public Fecha modificaFecha(String msg, Fecha f) {
        Fecha toret = new Fecha();
        int dia;
        int mes;
        int anho;
        boolean vacio = false;
        String info;

        System.out.println(msg);

        // dia        
        System.out.print("\t\tDia");
        if (f.getDia() > 0) {
            System.out.print("[" + f.getDia() + "]");
            vacio = true;
        }
        info = entrada.leeCadena(": ", vacio);
        if (info.length() > 0) {
            dia = Integer.parseInt(info);

            if (dia < 0 || dia > Fecha.MAXDIA) {
                throw new IllegalArgumentException("El día no puede ser negativo"
                        + " ni mayor que " + Fecha.MAXDIA);
            }
            toret.setDia(dia);
        } else {
            toret.setDia(f.getDia());
        }

        // mes          
        System.out.print("\t\tMes");
        if (f.getMes() > 0) {
            System.out.print("[" + f.getMes() + "]");
            vacio = true;
        }
        info = entrada.leeCadena(": ", vacio);
        if (info.length() > 0) {
            mes = Integer.parseInt(info);
            if (mes < 0 || mes > Fecha.MAXMES) {
                throw new IllegalArgumentException("El mes no puede ser negativo"
                        + " ni mayor que " + Fecha.MAXMES);
            }
            toret.setMes(mes);
        } else {
            toret.setMes(f.getMes());
        }

        // año
        System.out.print("\t\tAño");
        if (f.getAnho() > 0) {
            System.out.print("[" + f.getAnho() + "]");
            vacio = true;
        }
        info = entrada.leeCadena(": ", vacio);
        if (info.length() > 0) {
            anho = Integer.parseInt(info);
            if (Integer.toString(anho).length() != Fecha.CARANHO) {
                throw new IllegalArgumentException("El  año siempre se debe "
                        + "expresar con " + Fecha.CARANHO + " caracteres");
            }
            toret.setAnho(anho);
        } else {
            toret.setAnho(f.getAnho());
        }

        return toret;
    }

    /**
     * *** HORA ***
     */
    /**
     * Modifica/Lee los datos de una hora de tutoría.
     */
    public Hora modificaHora(String msg, Hora h) {
        Hora toret = new Hora();
        int hora;
        int minutos;
        boolean vacio = false;
        String info;

        System.out.println(msg);

        // Hora        
        System.out.print("\t\tHora ");
        if (h.getHora() > 0) {
            System.out.print("[" + h.getHora() + "]");
            vacio = true;
        }
        info = entrada.leeCadena(": ", vacio);
        if (info.length() > 0) {
            hora = Integer.parseInt(info);

            if (hora < 0 || hora > Hora.NUMHORAS) {
                throw new IllegalArgumentException("La hora no puede ser "
                        + "negativa ni mayor que " + Hora.NUMHORAS);
            }
            toret.setHora(hora);
        } else {
            toret.setHora(h.getHora());
        }

        // minutos       
        System.out.print("\t\tMinutos ");
        if (h.getMinutos() > 0) {
            System.out.print("[" + h.getMinutos() + "]");
            vacio = true;
        }
        info = entrada.leeCadena(": ", vacio);
        if (info.length() > 0) {
            minutos = Integer.parseInt(info);
            if (minutos < 0 || minutos > Hora.NUMMINUTOS) {
                throw new IllegalArgumentException("Los minutos no pueden ser "
                        + "negativos ni mayor que " + Hora.NUMMINUTOS);
            }
            toret.setMinutos(minutos);
        } else {
            toret.setMinutos(h.getMinutos());
        }

        return toret;
    }
}
