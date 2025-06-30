/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicobloqueii_2;

import nu.xom.Element;

/**
 *
 * @author Luis
 */
public abstract class Cliente {
    private String nombre;
    private String direccion;

    public Cliente(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }
    
    public Element toDom_raiz() {
        Element raiz = new Element ("cliente");
        Element nodoNombre = new Element ("nombre");
        Element nodoDireccion = new Element ("direccion");
        
        nodoNombre.appendChild(this.nombre);
        nodoDireccion.appendChild (this.direccion);
        
        raiz.appendChild(nodoNombre);
        raiz.appendChild(nodoDireccion);
        
        return raiz;
    }
    
    public abstract Element toDom();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente{");
        sb.append("nombre=").append(nombre);
        sb.append(", direccion=").append(direccion);
        sb.append('}');
        return sb.toString();
    }
    
    public abstract double calcularDescuento();
}
