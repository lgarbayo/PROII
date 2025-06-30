/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package es.uvigo.esei.proii.core;

/**
 *
 * @author dfabi
 */
public class FechaInvalidaException extends FechaException {

    /**
     * Creates a new instance of <code>FechaInvalidaException</code> without
     * detail message.
     */
    public FechaInvalidaException() {
    }

    /**
     * Constructs an instance of <code>FechaInvalidaException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FechaInvalidaException(String msg) {
        super(msg);
    }
}
