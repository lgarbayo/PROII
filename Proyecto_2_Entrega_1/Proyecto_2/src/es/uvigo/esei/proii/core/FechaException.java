/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package es.uvigo.esei.proii.core;

/**
 *
 * @author dfabi
 */
public class FechaException extends UniversidadException {

    /**
     * Creates a new instance of <code>FechaException</code> without detail
     * message.
     */
    public FechaException() {
    }

    /**
     * Constructs an instance of <code>FechaException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public FechaException(String msg) {
        super(msg);
    }
}
