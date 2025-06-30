
package es.uvigo.esei.proii.entidades;

/**
 *
 * @author Nanny
 */
public class Laboral extends Docente {
    private Fecha fechaInicio;
    private Fecha fechaFin;

    public Laboral(Fecha fechaInicio, Fecha fechaFin, 
            int dni, String nombre, 
            String nrp, int despacho) {
        super(dni, nombre, nrp, despacho);
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Fecha getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Fecha fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Fecha getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Fecha fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();
        
        toret.append("Laboral Docente.");
        toret.append(super.toString());
        toret.append("\tFecha Inicio: ").append(getFechaInicio());
        toret.append("\tFecha Fin: ").append(getFechaFin());

        return toret.toString();
    }
    
    
    
}
