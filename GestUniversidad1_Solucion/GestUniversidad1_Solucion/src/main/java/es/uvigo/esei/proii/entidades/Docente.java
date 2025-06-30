package es.uvigo.esei.proii.entidades;

import es.uvigo.esei.proii.entidades.TipoDedicacion;

public class Docente {

    public static final int MINDESPACHO = 100;
    public static final int MAXDESPACHO = 499;

    private String dni;
    private String nombre;
    private int despacho;
    private TipoDedicacion dedicacion;
//    private boolean tiempoCompleto;
//    private boolean tiempoParcial;

    /**
     * Crea un nuevo docente, con sus datos: dni, nombre, despacho y si su
     * dedicación es completa o parcial.
     *
     * @param dni D.N.I. del docente
     * @param nombre nombre completo del docente
     * @param despacho despacho del docente
     * @param dedicacion si es verdadera la dedicación es a tiempo completo (en
     * caso contrario, será a tiempo parcial)
     * @param dedicacion mediante un enumerado se especifica si es a tiempo
     * completo o parcial
     */
    public Docente(String dni, String nombre, int despacho,
            //            boolean dedicacion) {
            TipoDedicacion dedicacion) {
        setDni(dni);
        setNombre(nombre);
        setDespacho(despacho);
        setDedicacion(dedicacion);
//        setTiempoCompleto(dedicacion);
//        setTiempoParcial(!dedicacion);
    }

    /**
     * Devuelve el dni del docente
     *
     * @return el dni del docente, como String.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Cambia el D.N.I del docente
     *
     * @param dni el D.N.I. del docente
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Devuelve el nombre del docente
     *
     * @return el nombre del docente, como String.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre del docente
     *
     * @param nombre el nombre del docente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        if ((despacho < MINDESPACHO) || (despacho > MAXDESPACHO)) {
            throw new IllegalArgumentException ("Error. El numero "
                    + "de despacho debe"
                    + " estar comprendido entre " + MINDESPACHO + 
                    " y " + MAXDESPACHO);
        }
        this.despacho = despacho;
    }

    /**
     * Devuelve el valor de la etiqueta tiempoCompleto
     *
     * @return true si se encuentra activa, false en otro caso
     */
//    public boolean isTiempoCompleto() {
//        return tiempoCompleto;
//    }
    /**
     * Cambia la etiqueta tiempoCompleto
     *
     * @param tiempoCompleto El nuevo valor, como boolean
     */
//    public void setTiempoCompleto(boolean tiempoCompleto) {
//        this.tiempoCompleto = tiempoCompleto;
//        this.tiempoParcial = !tiempoCompleto;
//    }
    /**
     * Devuelve el valor de la etiqueta tiempoParcial
     *
     * @return true si se encuentra activa, false en otro caso
     */
//    public boolean isTiempoParcial() {
//        return tiempoParcial;
//    }
    /**
     * Cambia la etiqueta tiempoParcial
     *
     * @param tiempoParcial El nuevo valor, como boolean
     */
//    public void setTiempoParcial(boolean tiempoParcial) {
//        this.tiempoParcial = tiempoParcial;
//        this.tiempoCompleto = !tiempoParcial;
//    }
    /**
     * Devuelve el valor del enumerado dedicacion
     *
     * @return COMPLETA o PARCIAL
     */
    public TipoDedicacion getDedicacion() {
        return dedicacion;
    }

    /**
     * Cambia el enumerado dedicacion
     *
     * @param dedicacion El nuevo valor, como enumerado
     */
    public void setDedicacion(TipoDedicacion dedicacion) {
        this.dedicacion = dedicacion;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();

        toret.append("\nDocente. ");
        toret.append("\nD.N.I.: ").append(getDni());
        toret.append("\tNombre: ").append(getNombre());
        toret.append("\tDespacho: ").append(getDespacho());
//        if (isTiempoCompleto()) {
//            toret.append("\tDedicación: tiempo completo");
//        } else if (isTiempoParcial()) {
//            toret.append("\tDedicación: tiempo parcial");
//        }
        toret.append("\tDedicacion: ").append(getDedicacion());

        return toret.toString();
    }
}
