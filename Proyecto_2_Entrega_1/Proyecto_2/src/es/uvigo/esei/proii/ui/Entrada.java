package es.uvigo.esei.proii.ui;

import java.util.Scanner;

/**
 * Clase con métodos para facilitar la entrada de datos por teclado.
 */
public class Entrada {

    /**
     * Lee una cadena del teclado
     *
     * @param mensaje Literal que especifica lo que el usuario debe introducir
     * @return String cadena leida del teclado
     */
    public static String leeCadena(String mensaje) {
        return leeCadena(mensaje, false);
    }

    /**
     * Lee una cadena del teclado
     *
     * @param mensaje Literal que especifica lo que el usuario debe introducir
     * @param permiteVacia True: campo no obligatorio; False: campo obligatorio
     * @return String cadena leida del teclado
     */
    public static String leeCadena(String mensaje, boolean permiteVacia) {
        String leer;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print(mensaje);

            leer = scanner.nextLine().trim();

            if (!permiteVacia && leer.length() == 0) {
                System.err.println("La cadena introducida no puede estar vacía. "
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
    public static int leeEntero(String msg) {
        boolean esValido = false;
        int toret = 0;
        Scanner teclado = new Scanner(System.in);

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
}
