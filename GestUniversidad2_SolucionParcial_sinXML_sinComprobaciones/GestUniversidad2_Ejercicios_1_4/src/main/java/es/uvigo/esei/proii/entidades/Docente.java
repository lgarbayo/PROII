package es.uvigo.esei.proii.entidades;

public abstract class Docente extends Persona {

    private String nrp;
    private int despacho;

    /**
     * Crea un nuevo docente, con sus datos: dni, nombre, nrp y despacho
     *
     * @param dni D.N.I. del docente
     * @param nombre nombre completo del docente
     * @param despacho despacho del docente
     * @param nrp Numero de Registro Personal
     */
    public Docente(int dni, String nombre, String nrp, int despacho) {

        super(dni, nombre);
        setDespacho(despacho);
        this.nrp = nrp;
    }

    /**
     * Devuelve el despacho del docente
     *
     * @return el despacho del docente, como int.
     */
    public int getDespacho() {
        return despacho;
    }

    /**
     * Cambia el despacho del docente
     *
     * @param despacho el despacho del docente
     */
    public void setDespacho(int despacho) {
        this.despacho = despacho;
    }

    public String getNrp() {
        return nrp;
    }

    public void setNrp(String nrp) {
        this.nrp = nrp;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();

        toret.append(super.toString());
        toret.append("\tN.R.P.: ").append(getNrp());
        toret.append("\tDespacho: ").append(getDespacho());

        return toret.toString();
    }
}
