/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package es.uvigo.esei.proii.core;

/**
 *
 * @author dfabi
 */
public class MatriculaInvalidaException extends UniversidadException {

    /**
     * Creates a new instance of <code>MatriculaInvalidaException</code> without
     * detail message.
     */
    public MatriculaInvalidaException() {
    }

    /**
     * Constructs an instance of <code>MatriculaInvalidaException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public MatriculaInvalidaException(String msg) {
        super(msg);
    }
}
