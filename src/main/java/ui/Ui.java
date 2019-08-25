package com.leeyiyuan.ui;


import java.util.Scanner;

/**
 * Represents a console based user interface for interacting with the user.
 */
public class Ui {

    /** Scanner object used to communicate with console. */
    protected Scanner scanner;

    /**
     * Constructs a Ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows a welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Shows an empty line.
     */
    public void showLine() {
        System.out.println();
    }

    /**
     * Shows a string of text on a line.
     *
     * @param text String of text.
     */
    public void showLine(String text) {
        System.out.println(text);
    }

    /**
     * Shows a string of error text on a line.
     *
     * @param text String of text.
     */
    public void showError(String text) {
        System.out.println("Error: " + text);
    }

    /**
     * Shows the number of tasks.
     *
     * @param num Number of tasks.
     */
    public void showNumTasks(int num) {
        System.out.println(
                String.format("Now you have %d %s in the list.", num, num == 1 ? "task" : "tasks"));
    }

    /**
     * Returns a string of command text read from the Ui.
     *
     * @return String of command text read.
     * @throws UiException If there was an error in the Ui.
     */
    public String readCommand() throws UiException {
        if (this.scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            throw new UiException("UI stream ended.");
        }
    }
}
