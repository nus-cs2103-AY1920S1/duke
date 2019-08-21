package util;

import java.util.Scanner;

public class dukeInput {
    private static Scanner SCANNER = new Scanner(System.in);

    public static String readUserInput() {
        return SCANNER.nextLine();
    }


    public static void close() {
        SCANNER.close();
    }
}
