package duke.ui;

import java.util.Scanner;

/**
 * REDUNDANT
 * Represents the UI.
 */
public class Ui {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| |_| | | | | |/ / _ \\\n"
            + "|  _ _/ |_| |   <  __/\n"
            + "|_|   \\__,_|_|\\_\\___|\n";
    private static final String LOADING_ERROR_MSG = "WARNING! UNABLE TO LOAD "
            + "AND/OR DESERIALIZE TASKS FROM DATA DIRECTORY. "
            + "PLEASE CHECK THE FILEPATH.\n"
            + "YOU MAY CONTINUE TO WORK WITHOUT ANY OF YOUR PREVIOUS TASKS.";
    private Scanner sc;

    /**
     * Initializes the UI.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    public void greetUser() {
        displayOutput("Hello from\n" + LOGO);
    }

    /**
     * Displays Duke's response.
     * @param output Output to be displayed.
     * @return Output.
     */
    public String displayOutput(String output) {
        /*
        String bound = "     ____________________________________________________________\n     ";
        String intermediate = output.replace("\n", "\n     ");
        String finalOutput = bound + intermediate + "\n" + bound;
        System.out.println(finalOutput);
         */
        return getResponse(output);
    }

    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Shows loading error.
     */

    public void showLoadingError() {
        displayOutput(LOADING_ERROR_MSG);
    }

    /**
     * Gets response.
     * @param input String to respond to.
     * @return Response from Duke.
     */
    public String getResponse(String input) {
        return input;
    }

    /**
     * Checks if next input exists.
     * @return True if more inputs exist.
     */
    public boolean checkNextInput() {
        return sc.hasNext();
    }
}
