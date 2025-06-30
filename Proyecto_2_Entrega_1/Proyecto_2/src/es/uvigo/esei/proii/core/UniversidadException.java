
package es.uvigo.esei.proii.core;

public class UniversidadException extends RuntimeException {

    /**
     * Creates a new instance of <code>UniversidadException</code> without
     * detail message.
     */
    public UniversidadException() {
    }

    /**
     * Constructs an instance of <code>UniversidadException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UniversidadException(String msg) {
        super(msg);
    }
}
