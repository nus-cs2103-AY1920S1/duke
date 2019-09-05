package util;

import java.util.Scanner;

/***
 * <p>
 * Utility class used to read user input.
 * </p>
 */
public class DukeInput {
    private static Scanner SCANNER = new Scanner(System.in);

    /***
     * <p>
     * Listens to user input.
     * </p>
     * @return user's input.
     */
    public static String readUserInput() {
        return SCANNER.nextLine();
    }

    /***
     * <p>
     * Closes the input stream.
     * </p>
     */
    public static void close() {
        SCANNER.close();
    }
}
