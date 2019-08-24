package duke.ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        print("____________________________________________________________");
    }

    public void showError(String message) {
        print(" " + message);
    }

    public void showWelcome() {
        showLine();
        print(" Hello! I'm Duke");
        print(" What can I do for you?");
        showLine();
    }

    public void showLoadingError() {
        showLine();
        print(" The save file doesn't seem to be there or is incorrect!");
        print(" Let's start afresh.");
        showLine();
    }

    public void print() {
        System.out.println();
    }

    public void print(String message) {
        System.out.println(message);
    }
}
