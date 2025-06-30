package es.uvigo.esei.proii.entidades;

/**
 *
 * @author Nanny
 */
public class Hora {

    public static final int NUMHORAS = 23;
    public static final int NUMMINUTOS = 59;

    private int hora;
    private int minutos;

    public Hora(int hora, int minutos) {
        this.hora = hora;
        this.minutos = minutos;
    }

    public Hora() {
        this.hora = 0;
        this.minutos = 0;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();

        toret.append(getHora()).append(":").append(getMinutos());

        return toret.toString();
    }

}
