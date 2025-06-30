/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package es.uvigo.esei.proii.core;

/**
 *
 * @author dfabi
 */
public class HoraException extends UniversidadException {

    /**
     * Creates a new instance of <code>HoraException</code> without detail
     * message.
     */
    public HoraException() {
    }

    /**
     * Constructs an instance of <code>HoraException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public HoraException(String msg) {
        super(msg);
    }
}
