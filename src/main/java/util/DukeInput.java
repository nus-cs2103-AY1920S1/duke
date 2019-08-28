package util;

import java.util.Scanner;

/***
 * Utility class used to read user input.
 */
public class DukeInput {
    private static Scanner SCANNER = new Scanner(System.in);

    /***
     * Listens to user input.
     * @return user's input.
     */
    public static String readUserInput() {
        return SCANNER.nextLine();
    }

    /***
     * Closes the input stream.
     */
    public static void close() {
        SCANNER.close();
    }
}
