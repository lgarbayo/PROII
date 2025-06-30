/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package es.uvigo.esei.proii.core;


/**
 *
 * @author dfabi
 */
public class NRPInvalidoException extends UniversidadException {

    /**
     * Creates a new instance of <code>NRPInvalidoException</code> without
     * detail message.
     */
    public NRPInvalidoException() {
    }

    /**
     * Constructs an instance of <code>NRPInvalidoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NRPInvalidoException(String msg) {
        super(msg);
    }
}
