package duke;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void printMessage(String message) {
        String[] messages = message.split("\n");
        System.out.println("    ____________________________________________________________");
        for (String line : messages) {
            System.out.println("     " + line);
        }
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public void printError(Exception e) {
        printMessage("☹ OOPS!!! " + e.getMessage());
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        printMessage("Hello! I'm duke.Duke\nWhat can I do for you?");
    }

    public static String pluralize(String item, Integer quantity) {
        if (quantity == 1) {
            return "1 " + item;
        } else {
            return quantity + " " + item + "s";
        }
    }
}
