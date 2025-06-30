
package es.uvigo.esei.proii.core;


public class NoEliminableException extends UniversidadException {

    /**
     * Creates a new instance of <code>NoEliminableException</code> without
     * detail message.
     */
    public NoEliminableException() {
    }

    /**
     * Constructs an instance of <code>NoEliminableException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoEliminableException(String msg) {
        super(msg);
    }
}
