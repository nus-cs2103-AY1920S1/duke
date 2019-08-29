package components;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";

    /**
     * Prints a string in blue.
     *
     * @param str is the string to print.
     */
    public static void print(String str) {
        System.out.println(ANSI_BLUE + str + ANSI_RESET);
    }

    /**
     * Prints an error message in red.
     */
    public static void printErr(String str) {
        System.out.println(ANSI_RED + str + ANSI_RESET);
    }

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        print("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Closes the scanner.
     */
    public void closeUi() {
        print("Bye. Hope to see you again soon!");
        scanner.close();
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }


    public void showLine() {
        print("_______________________________________________");
    }
}
