
package es.uvigo.esei.proii.entidades;

/**
 *
 * @author Nanny
 */
public class Funcionario extends Docente {
    private Fecha fechaPosesion;

    public Funcionario(Fecha fechaPosesion, int dni, String nombre, 
            String nrp, int despacho) {
        super(dni, nombre, nrp, despacho);
        this.fechaPosesion = fechaPosesion;
    }

    public Fecha getFechaPosesion() {
        return fechaPosesion;
    }

    public void setFechaPosesion(Fecha fechaPosesion) {
        this.fechaPosesion = fechaPosesion;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();
        
        toret.append("Docente Funcionario.");
        toret.append(super.toString());
        toret.append("\tFecha de Posesion: ").append(getFechaPosesion());

        return toret.toString();
    }    
     
}
