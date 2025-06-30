package es.uvigo.esei.proii.iu;

import java.util.Scanner;

public class Entrada {

    private Scanner teclado = new Scanner(System.in);

    /**
     * Lee una cadena del teclado
     *
     * @param mensaje Literal que especifica lo que el usuario debe introducir
     * @return String cadena leida del teclado
     */
    public String leeCadena(String mensaje) {
        return leeCadena(mensaje, false);
    }

    /**
     * Lee una cadena del teclado
     *
     * @param mensaje Literal que especifica lo que el usuario debe introducir
     * @param permiteVacia True: campo no obligatorio; False: campo obligatorio
     * @return String cadena leida del teclado
     */
    public String leeCadena(String mensaje, boolean permiteVacia) {
        String leer;

        do {
            System.out.print(mensaje);

            leer = teclado.nextLine().trim();

            if (!permiteVacia && leer.length() == 0) {
                throw new IllegalArgumentException("La cadena introducida "
                        + "no puede estar vacía. "
                        + "Por favor, introdúcela de nuevo.");
            }
        } while ((permiteVacia == false) && leer.length() == 0);

        return leer;

    }

    /**
     * Lee un número entero de teclado, mostrando un mensaje que indica lo que
     * el usuario debe introducir.
     *
     * @param msg el mensaje a visualizar
     * @return el número, como entero
     */
    public int leeEntero(String msg) {
        boolean esValido = false;
        int toret = 0;

        do {
            System.out.print(msg);

            try {
                toret = Integer.parseInt(teclado.nextLine());
                esValido = true;
            } catch (NumberFormatException exc) {
                System.err.println("La cadena introducida no se puede "
                        + "convertir a número entero. Por favor, "
                        + "introdúcela de nuevo.");
            }
        } while (!esValido);

        return toret;

    }

    /**
     * Lee un número entero de teclado, solo en el intervalo [min, max],
     * mostrando un mensaje que indica lo que el usuario debe introducir.
     *
     * @param msg el mensaje a visualizar
     * @param min el valor mínimo del intervalo
     * @param max el valor máximo del intervalo
     */
    public int leeEntero(String msg, int min, int max) {
        boolean esValido = false;
        int toret = 0;
        do {
            toret = leeEntero(msg + "[" + min + ", " + max + "]: ");

            if (toret < min || toret > max) {
                esValido = false;
                throw new IllegalArgumentException("Debes introducir "
                        + "un valor entre "
                        + min + " y " + max + " ");
            } else {
                esValido = true;
            }
        } while (!esValido);

        return toret;
    }

    /*public int leeOpcion(String msg, String[] opciones) {

    }*/
}
