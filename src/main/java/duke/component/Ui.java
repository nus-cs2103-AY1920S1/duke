package duke.component;

import java.util.Scanner;

/**
 * Represents Ui object that is responsible for the User interface
 * and chatbot interacting with the user.
 * The 'Ui' class supports operators (i) reading user input,
 * (ii) showing welcome message,
 * (iii) showing line border
 * (iv) showing loading error if the text file is unable to load,
 * (v) closing user interface,
 * (vi) printing text on console,
 * and (vii) printing error message on console.
 */
public class Ui {

    /** Parser to parse user inputs */
    private Parser inputParser = new Parser();

    /** Scanner to scan user inputs */
    private Scanner scanner = new Scanner(System.in);

    /** Checks whether Ui should close */
    boolean isExit = false;

    /**
     * Reads user inputs
     * @return String that represents user inputs.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        this.showLine();
        this.printText("Hello! I'm Duke\n" +
                "What can I do for you?");
        this.showLine();
    }

    /**
     * Prints border line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }


    /**
     * Prints loading error message.
     */
    public void showLoadingError() {
        System.out.println("Unable to load");
    }

    /**
     * Closes Ui and stops interaction with user.
     */
    public void closeUi() {
        this.isExit = true;
        this.scanner.close();
    }


    /**
     * Prints text in console.
     * @param text Text to print.
     */
    public void printText(String text) {

        System.out.println(text);

    }


    /**
     * Prints error message
     * @param message Message to print.
     */
    public void showError(String message) {
        System.out.println(message);
    }
}


