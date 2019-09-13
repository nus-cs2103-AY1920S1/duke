package duke.util;

import java.util.Scanner;

public class Ui {

    public Ui() { }

    public static void showWelcome() {
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I OOP for you today?");
    }

    public static void showLoading(int size) {
        System.out.println("Loaded " + size + (size == 1 ? "task" : " tasks") + System.lineSeparator());
    }

    public static void showLoadingError(String message) {
        System.out.println(message + System.lineSeparator());
    }

    public static void showError(String message) {
        System.out.println(message + System.lineSeparator());
    }

    public static void showLine() {
        System.out.println("_______");
    }

    public static void showBye() {
        System.out.println("Goodbye... Until more OOP.");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNext()) {
            return sc.nextLine();
        }
        return "bye";
    }
}
