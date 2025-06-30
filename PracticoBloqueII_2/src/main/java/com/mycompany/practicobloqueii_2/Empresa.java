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
public class Empresa extends Cliente{
    private double facturacionPrevia;

    public Empresa(double facturacionPrevia, String nombre, String direccion) {
        super(nombre, direccion);
        this.facturacionPrevia = facturacionPrevia;
    }
    
    public double calcularDescuento() {
        if (facturacionPrevia > 5000)
            return 0.11;
        else
            return 0.10;
    }

    public Element toDOM() {
        Element raiz = super.toDOM_raiz();
        Element tipoCliente = new Element("tipoCliente");
        tipoCliente.appendChild("empresa");

        Element ELfacturacionPrevia = new Element("facturacionPrevia");
        ELfacturacionPrevia.appendChild(Double.toString(this.facturacionPrevia));

        raiz.appendChild(tipoCliente);
        raiz.appendChild(ELfacturacionPrevia);

        return raiz;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empresa{");
        sb.append("facturacionPrevia=").append(facturacionPrevia);
        sb.append('}');
        sb.append(super.toString());
        return sb.toString();
    }
    
    
}
