
package es.uvigo.esei.proii.entidades;

/**
 *
 * @author Nanny
 */
public enum TipoDedicacion {
    COMPLETA (240),
    PARCIAL (150);
    
    private final int numHoras;
    
    private TipoDedicacion (int numHoras){
        this.numHoras = numHoras;
    }
    
    public String toString(){
        String dedicacion = name();
        String parte = dedicacion.substring(1).toLowerCase();

        return dedicacion.charAt(0) + parte;
    }
    
    public int getNumHoras(){
        return numHoras;
    }
    
}
