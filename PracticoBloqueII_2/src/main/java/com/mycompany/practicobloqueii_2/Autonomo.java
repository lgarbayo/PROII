/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicobloqueii_2;

/**
 *
 * @author Luis
 */
public class Autonomo extends Cliente{
    public Autonomo(String nombre, String direccion) {
        super(nombre, direccion);
    }
    
    public double calcularDescuento() {
        return 0.01;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Autonomo{");
        sb.append('}');
        sb.append(super.toString());
        return sb.toString();
    }
    
    
    
}
