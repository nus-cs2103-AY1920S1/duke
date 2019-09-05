package util;

import java.util.Scanner;

/***
 * <p>
 * Utility class used to read user input.
 * </p>
 */
public class DukeInput {
    private static Scanner SCANNER = new Scanner(System.in);
    private static DukeInputHandler handler;
    private static DukeInputImplementation implementation;

    /***
     * <p>
     * Configures DukeInput to use alternative input implementation.
     * </p>
     * @param implementation class that handles user input.
     */
    public static void setUpDukeInput(DukeInputImplementation implementation) {
        DukeInput.implementation = implementation;
    }

    public static void setUpDukeInputHandler(DukeInputHandler handler) {
        DukeInput.handler = handler;
    }

    /***
     * <p>
     * Listens to user input.
     * </p>
     * @return user's input.
     */
    public static String readUserInput() {
        if (implementation == null) {
            return SCANNER.nextLine();
        } else {
            return implementation.getDukeInput();
        }
    }

    /***
     * <p>
     * Closes the input stream.
     * </p>
     */
    public static void close() {
        if (implementation != null) {
            implementation.close();
        }

        SCANNER.close();
    }
}
