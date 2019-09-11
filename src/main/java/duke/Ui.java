package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidInputException;

import java.text.ParseException;
import java.util.Scanner;

/**
 * Duke.Ui class deals with the interactions with the user.
 */
public class Ui {
    
    
    /**
     * Class constructor.
     */
    public Ui() {
    }
    
    /**
     * This method prints the welcome message for the user when they first
     * enter the program. It is always shown to signal the start of the program.
     */
    public static String welcome() {
        return "Hello! I'm Bear. \nWhat can I do for you?";
    }
    
    /**
     * This method reads the input by the user.
     *
     * @return string input as keyed in by the user.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }
    
    /**
     * This method prints the error message accordingly to the different exceptions.
     *
     * @param e and handles the exceptions accordingly.
     */
    public void showError(Exception e) {
        if (e instanceof InvalidInputException) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (e instanceof EmptyDescriptionException) {
            System.out.println(String.format("OOPS!!! The description of a %s cannot be empty.", e.getMessage()));
        } else if (e instanceof InvalidDescriptionException) {
            System.out.println(String.format("OOPS!!! Invalid input! Make sure your %s has a description and "
                + "required" + " data after /at for Event, /by for Deadline or data before and after 'doafter'\n",
                e.getMessage()));
        } else if (e instanceof ParseException) {
            System.out.println(String.format("Please write your deadline/event date in this format: dd/MM/yyyy HH:mm,"
                + " example: 02/08/2019 14:30\n", e.getMessage()));
        } else {
            System.out.println(e.getMessage());
        }
    }
}
