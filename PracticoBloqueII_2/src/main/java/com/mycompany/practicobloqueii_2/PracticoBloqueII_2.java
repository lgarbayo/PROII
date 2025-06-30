/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.practicobloqueii_2;

/**
 *
 * @author Luis
 */
public class PracticoBloqueII_2 {

    public static void main(String[] args) {
        System.out.println("45145426Z, LGF, PROII3, codigo");
        
        Propietario p = new Propietario("luis", "32004");
        LibroClientes libro1 = new LibroClientes(p);
        
        Autonomo a1 = new Autonomo("luis", "222");
        Autonomo a2 = new Autonomo("roberto", "2221");
        
        Particular p1 = new Particular (4, "luisa", "2222");
        Particular p2 = new Particular (9, "ruperta", "2222");
        
        Empresa e1 = new Empresa (8000, "theo", "argentina");
        Empresa e2 = new Empresa (5555000, "ancelotti", "avenida campeona");
        
        libro1.insertarCliente(a1);
        libro1.insertarCliente(a2);
        libro1.insertarCliente(p1);
        libro1.insertarCliente(p2);
        libro1.insertarCliente(e1);
        libro1.insertarCliente(e2);
    }
}
