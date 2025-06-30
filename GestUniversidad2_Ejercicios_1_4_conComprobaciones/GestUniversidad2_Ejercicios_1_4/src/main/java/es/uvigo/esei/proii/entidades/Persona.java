package es.uvigo.esei.proii.entidades;

/**
 *
 * @author Nanny
 */
public abstract class Persona {

    private int dni;
    private String nombre;

    public Persona(int dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();

        toret.append("\nD.N.I.: ").append(getDni());
        toret.append("\tNombre: ").append(getNombre());

        return toret.toString();
    }

}
