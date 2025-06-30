package es.uvigo.esei.proii.entidades;

/**
 *
 * @author Nanny
 */
public class Estudiante extends Persona{

    private String numeroMatricula;

    public Estudiante(String numeroMatricula, int dni, String nombre) {
        super(dni, nombre);
        this.numeroMatricula = numeroMatricula;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();

        toret.append("Estudiante. ");
        toret.append(super.toString());
        toret.append("\tNumero Matricula: ")
                .append(getNumeroMatricula());
       
        return toret.toString();
    }
}
