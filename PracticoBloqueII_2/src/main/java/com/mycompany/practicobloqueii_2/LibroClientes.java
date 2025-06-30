/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicobloqueii_2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis
 */
public class LibroClientes {
    private List<Cliente> clientes;
    private Propietario propietario;

    public LibroClientes(Propietario propietario) {
        this.clientes = new ArrayList<>();
        this.propietario = propietario;
    }

    public List<Cliente> getLibros() {
        return clientes;
    }

    public Propietario getPropietarios() {
        return propietario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LibroClientes{");
        sb.append("libros=").append(clientes);
        sb.append(", propietario=").append(propietario);
        sb.append('}');
        return sb.toString();
    }
    
    public void insertarCliente (Cliente c) {
        this.clientes.add(c);
    }
}
