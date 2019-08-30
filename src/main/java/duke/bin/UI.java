package duke.bin;

import duke.bin.common.Constants;
import duke.bin.common.DukeException;
import duke.bin.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles output and input via terminal for the application
 */
public class UI {
    private Scanner sc;

    /**
     * Default Constructor will instantiate a scanner object.
     */
    public UI () {
        sc = new Scanner(System.in);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        display("What can I do for you?", "Hello! I'm Duke");
    }

    /**
     * Displays the error message to the user.
     *
     * @param exception exception that was thrown to be displayed
     */
    public void showError(DukeException exception) {
        display(exception.getMessage());
    }

    /**
     * Reads what the user has inputted.
     *
     * @return the input in String format
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays the message to the user.
     *
     * @param string the message to be displayed.
     */
    public void display(String string) {
        System.out.println(Constants.HORIZONTAL_LINE + Constants.INDENTATION + string + "\n" + Constants.HORIZONTAL_LINE);
    }

    /**
     * Displays the message to the user. Overloaded for start phrase to be displayed.
     *
     * @param string the message to be displayed.
     * @param startPhrase the start message to be displayed before the original message.
     */
    public void display(String string, String startPhrase) {
        System.out.println(Constants.HORIZONTAL_LINE + Constants.INDENTATION + startPhrase);
        System.out.println(Constants.INDENTATION + string + "\n" + Constants.HORIZONTAL_LINE);
    }

    /**
     * Displays the message to the user. Overloaded for start and end phrase to be displayed.
     *
     * @param string the message to be displayed.
     * @param startPhrase the start message to be displayed before the original message.
     * @param endingPhrase the end message to be displayed after the original message.
     */
    public void display(String string, String startPhrase, String endingPhrase) {
        System.out.println(Constants.HORIZONTAL_LINE + Constants.INDENTATION + startPhrase);
        System.out.println(Constants.INDENTATION + "  " + string);
        System.out.println(Constants.INDENTATION + endingPhrase + "\n" + Constants.HORIZONTAL_LINE);
    }

    /**
     * Displays the list to the user in indexed format with a starting message.
     *
     * @param tasks the task list to be displayed.
     * @param startPhrase the starting message to be displayed.
     */
    public void displayList(ArrayList<Task> tasks, String startPhrase) {
        System.out.println(Constants.HORIZONTAL_LINE + Constants.INDENTATION + startPhrase);
        int i = 1;
        while( i < tasks.size()) {
            System.out.println(Constants.INDENTATION + i + ". " + tasks.get(i - 1));
            i++;
        }
        System.out.println(Constants.INDENTATION + i + ". " + tasks.get(i - 1));
        System.out.println(Constants.HORIZONTAL_LINE);
    }
}
