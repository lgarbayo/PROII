package es.uvigo.esei.proii.entidades;

/**
 *
 * @author Nanny
 */
public class Fecha {

    public static final int MAXDIA = 31;
    public static final int MAXMES = 12;
    public static final int CARANHO = 4;

    private int dia;
    private int mes;
    private int anho;

    public Fecha(int dia, int mes, int anho) {
        this.dia = dia;
        this.mes = mes;
        this.anho = anho;
    }

    public Fecha() {
        this.dia = 0;
        this.mes = 0;
        this.anho = 0;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnho() {
        return anho;
    }

    public void setAnho(int anho) {
        this.anho = anho;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();

        toret.append(getDia()).append("/");
        toret.append(getMes()).append("/");
        toret.append(getAnho());

        return toret.toString();
    }

}
