/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package es.uvigo.esei.proii.excepciones;

/**
 *
 * @author ncamb
 */
public class NoExisteEstudianteException extends RuntimeException {

    /**
     * Constructs an instance of <code>NoExisteEstudianteException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public NoExisteEstudianteException(String msg) {
        super(msg);
    }
}
