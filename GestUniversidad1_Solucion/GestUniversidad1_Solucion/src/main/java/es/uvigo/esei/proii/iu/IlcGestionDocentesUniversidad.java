package es.uvigo.esei.proii.iu;

import es.uvigo.esei.proii.entidades.TipoDedicacion;
import es.uvigo.esei.proii.entidades.Docente;
import es.uvigo.esei.proii.entidades.Universidad;

public class IlcGestionDocentesUniversidad {

    private Universidad universidad;
    private Entrada entrada;

    public IlcGestionDocentesUniversidad(Universidad universidad, 
            Entrada entrada) {
        this.universidad = universidad;
        this.entrada = entrada;
    }

    /**
     * Muestra las distintas opciones para gestionar los docentes y lanza los
     * métodos que realiza cada operación
     */
    public void gestionDocentes() {
        int opcion = 0;
TipoDedicacion dedicacion;

        do {
            opcion = menuGestionDocentes();
            switch (opcion) {
                case 1:
                    insertaDocente();
                    break;
                case 2:
                    modificaDocente();
                    break;
                case 3:
                    eliminaDocente();
                    break;
                case 4:
                    listarDocentes();
                    break;
                case 5:
                    dedicacion = leeDedicacionDocente ("\nIntroduce el "
                            + "tipo de dedicacion de los docentes "
                            + "a listar.");
                    System.out.println(listarDocentesDedicacion(dedicacion));
                    break;
            }
        } while (opcion != 0);
    }

    /**
     * Presenta un menu con las opciones para gestionar docentes, y permite
     * seleccionar una.
     *
     * @return la opcion seleccionada, como entero
     */
    private int menuGestionDocentes() {
        int toret;

        do {
            System.out.println("GESTION DOCENTES");
            System.out.println(
                    "\n1. Insertar docente\n"
                    + "2. Modificar docente\n"
                    + "3. Eliminar docente\n"
                    + "4. Listar docentes\n"
                    + "5. Listar docentes por dedicacion\n"
                    + "0. Volver al menú principal\n");
            toret = entrada.leeEntero("Selecciona: ");
        } while (toret < 0
                || toret > 5);

        System.out.println();
        return toret;
    }

    /**
     * Crea un nuevo docente y lo inserta en la universidad
     *
     */
    private void insertaDocente() {
        System.out.println("\n-------------");
        System.out.println("\nAlta docente");

        Docente p = leeDocente();
        universidad.insertaDocente(p);
    }

    /**
     * Lee del teclado los datos de un docente.
     *
     * @return El objeto Docente creado
     */
    private Docente leeDocente() {
        System.out.println("\nIntroduce los datos del nuevo docente:");

        String dni = entrada.leeCadena("\tD.N.I.: ");
        String nombre = entrada.leeCadena("\tNombre: ");
        int despacho = entrada.leeEntero("\tDespacho: ", Docente.MINDESPACHO,
                Docente.MAXDESPACHO);
        TipoDedicacion dedicacion = leeDedicacionDocente("\tIntroduce dedicacion: ");

//        String dedicacionDocente = leeDedicacionDocente("\tIntroduce el tipo "
//                + "de dedicacion del docente (C: completa, P: parcial): ");
//        boolean esTiempoCompleto = dedicacionDocente.equals("C");
//        return new Docente(dni, nombre, despacho, esTiempoCompleto);
        return new Docente(dni, nombre, despacho, dedicacion);
    }

    /**
     * Lee la dedicación del docente
     *
     * @param msg Mensaje que se muestra al usuario
     * @return La cadena que indica la dedicación del docente
     */
//    private String leeDedicacionDocente(String msg) {
//        String dedicacionDocente = "";
//        do {
//            dedicacionDocente = entrada.leeCadena(msg).toUpperCase();
//
//            if (!dedicacionDocente.equals("C")
//                    && !dedicacionDocente.equals("P")) {
//                System.err.println("La dedicación del docente introducida"
//                        + " no es válida. "
//                        + "Por favor, introdúcela de nuevo.");
//            }
//        } while (!dedicacionDocente.equals("C")
//                && !dedicacionDocente.equals("P"));
//
//        return dedicacionDocente;
//    }
    /**
     * Lee la dedicación del docente como un enumerado
     *
     * @param msg Mensaje que se muestra al usuario
     * @return El enumerado que indica la dedicación del docente
     */
    private TipoDedicacion leeDedicacionDocente(String msg) {
        int opcion;

        for (TipoDedicacion d : TipoDedicacion.values()) {
            System.out.print("\tIntroduce " + (d.ordinal() + 1)
                    + " para " + d.toString());
        }
        opcion = entrada.leeEntero(msg, 1, TipoDedicacion.values().length);

        return TipoDedicacion.values()[opcion - 1];
    }

    /**
     * Borra un docente por su posicion en la universidad.
     *
     */
    private void eliminaDocente() {
        if (universidad.getNumDocentes() > 0) {
            universidad.eliminaDocente(leePosDocente());
        } else {
            System.out.println("La universidad no contiene docentes.");
        }
    }

    /**
     * Modifica un docente existente.
     *
     */
    private void modificaDocente() {
        System.out.println("\n---------------------");
        System.out.println("\nModificación docente");

        if (universidad.getNumDocentes() > 0) {
            modificaDocente(universidad.getDocente(leePosDocente()));
        } else {
            System.out.println("La universidad no contiene docentes.");
        }
    }

    private void modificaDocente(Docente p) {
        String info;

        System.out.println("\nModificando los datos del siguiente docente:");
        System.out.println(p);
        System.out.println();

        // D.N.I.
        System.out.print("D.N.I. ");
        if (p.getDni().length() > 0) {
            System.out.print("[" + p.getDni() + "]");
        }
        info = entrada.leeCadena(": ", p.getDni().length() > 0);
        if (info.length() > 0) {
            p.setDni(info);
        }

        // Nombre
        System.out.print("Nombre ");
        if (p.getNombre().length() > 0) {
            System.out.print("[" + p.getNombre() + "]");
        }
        info = entrada.leeCadena(": ", p.getNombre().length() > 0);
        if (info.length() > 0) {
            p.setNombre(info);
        }

        // Despacho
        do {
            System.out.print("Despacho ");
            System.out.print("[" + p.getDespacho() + "]");
            info = entrada.leeCadena(": ", true);
        } while ((info.length() > 0)
                && ((Integer.parseInt(info) < Docente.MINDESPACHO)
                || (Integer.parseInt(info) > Docente.MAXDESPACHO)));
        if (info.length() > 0) {
            p.setDespacho(Integer.parseInt(info));
        }

        // Dedicacion. ES OBLIGATORIO MODIFICAR (INTRODUCIR) LA DEDICACION
//        String dedicacionActual = p.isTiempoCompleto() ? "Completa" : "Parcial";
//        String dedicacionDocente = leeDedicacionDocente("\tIntroduce la dedicacion"
//                + " del docente (C: Completa, P: parcial) ["
//                + dedicacionActual + "]: ");
//        boolean esCompleta = dedicacionDocente.equals("C");
//        p.setTiempoCompleto(esCompleta);
//        p.setTiempoParcial(!esCompleta);
        TipoDedicacion dedicacionActual = p.getDedicacion();
        TipoDedicacion dedicacion = leeDedicacionDocente("\tIntroduce "
                + "dedicacion del docente [" + dedicacionActual + "]: ");
        p.setDedicacion(dedicacion);
    }

    /**
     * Lee del teclado la posición de un docente en la colección
     *
     * @return la posición del docente, como entero.
     */
    private int leePosDocente() {
        return entrada.leeEntero("Introduzca posición del docente", 1, universidad.getNumDocentes()) - 1;
    }

    /**
     * Visualiza los docentes almacenados en la universidad por la salida std.
     *
     */
    void listarDocentes() {
        final int numDocentes = universidad.getNumDocentes();

        if (numDocentes > 0) {
            System.out.print("\nDocentes:\n");
            for (int i = 0; i < numDocentes; i++) {
                System.out.print((i + 1) + ". ");
                System.out.println(universidad.getDocente(i).toString());
                System.out.println("\n");
            }
        } else {
            System.out.println("No hay docentes.");
        }

    }

    /**
     * Lista los docentes de la universidad por dedicacion
     *
     */
    String listarDocentesDedicacion(TipoDedicacion dedicacion) {
        int numDocentes = universidad.getNumDocentes();
        StringBuilder toret = new StringBuilder ();
                
        for (int i = 0; i < numDocentes; i++) {
            if (universidad.getDocente(i).getDedicacion().equals(dedicacion)){
                toret.append(universidad.getDocente(i).toString());
            }
        }
        
        if (toret.isEmpty()){
            toret.append("No hay docentes con dedicacion ")
                    .append(dedicacion.toString());
        }
        return toret.toString();
    }
}
