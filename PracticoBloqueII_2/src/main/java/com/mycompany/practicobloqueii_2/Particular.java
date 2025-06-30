/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicobloqueii_2;

/**
 *
 * @author Luis
 */
public class Particular extends Cliente{
    private int anoAntiguedad;

    public Particular(int anoAntiguedad, String nombre, String direccion) {
        super(nombre, direccion);
        this.anoAntiguedad = anoAntiguedad;
    }
    
    public double calcularDescuento() {
        if (anoAntiguedad < 2)
            return 0.01;
        else if (anoAntiguedad >= 2 && anoAntiguedad < 5)
            return 0.05;
        else
            return 0.07;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Particular{");
        sb.append("anoAntiguedad=").append(anoAntiguedad);
        sb.append('}');
        sb.append(super.toString());
        return sb.toString();
    }
    
    
}
