package cs2103t.duke.ui;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Represents the System I/O interface and layout of Duke chatbot.
 * This handles user input and Duke output.
 */
public class Ui {
    /** Divides responses as part of layout. */
    private static final String DIVIDER = "    " + "-".repeat(61);
    /** Reads user inputs from console. */
    private Scanner scannerIn;

    /**
     * Constructs a Ui.
     */
    public Ui() {
        this.scannerIn = new Scanner(System.in);
    }

    /**
     * Shows welcome response when Duke starts up.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dukeRespond("Hello! I'm Duke", "What can I do for you?");
    }

    /**
     * Prints to console the inputs in Duke layout.
     * @param inputs lines of String that Duke is supposed to respond.
     */
    public void dukeRespond(String... inputs) {
        showLine();
        PrintStream out;
        try {
            out = new PrintStream(System.out, false, "UTF-8");
            for (String str : inputs) {
                out.println("     " + str);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); //shouldnt happen?
        }
        showLine();
    }

    private void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Shows loading error when file cannot be read.
     */
    public void showLoadingError() {
        dukeRespond("Cannot read from file! Check if it exists?");
    }

    /**
     * Shows error when an Exception is caught.
     * @param msg message of the exception caught.
     */
    public void showError(String msg) {
        dukeRespond(msg);
    }

    /**
     * Waits for and reads next line of user input.
     * @return user input.
     */
    public String readCommand() {
        //start listening for user input
        return this.scannerIn.nextLine();
    }

    /**
     * Closes scanner before terminating Duke.
     */
    public void closeScanner() {
        this.scannerIn.close();
    }
}
