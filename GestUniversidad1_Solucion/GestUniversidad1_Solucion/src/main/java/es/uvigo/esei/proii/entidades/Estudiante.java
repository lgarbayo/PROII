package es.uvigo.esei.proii.entidades;

/**
 *
 * @author Nanny
 */
public class Estudiante {

    private String nombre;
    private String dni;
    private boolean esErasmus;

    /**
     * Crea un nuevo estudiante, con sus datos: dni, nombre y si es o no de
     * Erasmus.
     *
     * @param dni D.N.I. del estudiante
     * @param nombre nombre completo del estudiante
     * @param esErasmus si es verdadera el estudiante es de Erasmus (en caso
     * contrario, no será de Erasmus)
     */
    public Estudiante(String dni, String nombre, boolean esErasmus) {
        setNombre(nombre);
        setDni(dni);
        setEsErasmus(esErasmus);
    }

    /**
     * Devuelve el nombre del estudiante
     *
     * @return el nombre del estudiante, como String.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre del estudiante
     *
     * @param nombre el nombre del estudiante
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el dni del estudiante
     *
     * @return el dni del estudiante, como String.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Cambia el D.N.I del estudiante
     *
     * @param dni el D.N.I. del estudiante
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Devuelve el valor de la etiqueta esErasmus
     *
     * @return true si se encuentra activa, false en otro caso
     */
    public boolean isEsErasmus() {
        return esErasmus;
    }

    /**
     * Cambia la etiqueta esErasmus
     *
     * @param esErasmus El nuevo valor, como boolean
     */
    public void setEsErasmus(boolean esErasmus) {
        this.esErasmus = esErasmus;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();

        toret.append("Estudiante. ");
        toret.append("\nD.N.I.: ").append(getDni());
        toret.append("\tNombre: ").append(getNombre());
        toret.append("\tErasmus: ");
        if (isEsErasmus()) {
            toret.append("Si");
        } else {
            toret.append("No");
        }

        return toret.toString();
    }
}
