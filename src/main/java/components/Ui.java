package components;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    /**
     * Prints a string.
     */
    public static void print(String str) {
        System.out.println(str);
    }

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        print("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void closeUi() {
        print("Bye. Hope to see you again soon!");
        scanner.close();
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public int readIndex() {
        try {
            return scanner.nextInt() - 1;
        } catch (InputMismatchException e) {
            print("OOPS!!! You need to enter a natural number.");
            return -1;
        }
    }

    public void showLine() {
        print("_________________________");
    }
}
