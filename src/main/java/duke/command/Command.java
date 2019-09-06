package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidInputException;

import java.io.IOException;
import java.text.ParseException;

/**
 * An abstract Command class with abstract method execute and isExit.
 * This Command class handles the Duke.command given by the user.
 */
public abstract class Command {
    
    /**
     * Abstract method that is the main function of the Command class.
     * Respective subclass of Command (classes which inherits from Command) will have this execute
     * function based on their Duke.command, and carry out the respective task as per the Duke.command.
     *
     * @param tasks   ArrayList of Tasks that keep tracks of the Tasks.
     * @param storage Handles the reading and writing of the txt file.
     * @throws EmptyDescriptionException   if input does not contain description for the task.
     * @throws InvalidInputException       if input does not qualify for any of the functions in the program.
     * @throws InvalidDescriptionException if input does not follow the required format.
     * @throws IOException                 if storage file for tracking the Duke.TaskList is not found.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws EmptyDescriptionException,
        InvalidInputException, InvalidDescriptionException, IOException;
    
    /**
     * Abstract method that is most applicable to specifically the ByeCommand Class.
     *
     * @return boolean value which decides whether the app will be exiting.
     */
    public abstract boolean isExit();
    
    /**
     * Handles the Duke.exception thrown by respective Command execution.
     * Takes care of InvalidInputException, EmptyDescriptionException, InvalidDescriptionException,
     * Parse Exception and other unhandled Exceptions.
     *
     * @param e Exception thrown when user input is wrong.
     */
    public static String handleException(Exception e) {
        if (e instanceof InvalidInputException) {
            return ("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (e instanceof EmptyDescriptionException) {
            return (String.format("OOPS!!! The description of a %s cannot be empty.", e.getMessage()));
        } else if (e instanceof InvalidDescriptionException) {
            return (String.format("OOPS!!! Invalid input! Make sure your %s has a description and required" + " data "
                + "after /at for Event or /by for Deadline.\n", e.getMessage()));
        } else if (e instanceof ParseException) {
            return (String.format("Please write your deadline/event date in this format: dd/MM/yyyy HH:mm," + " "
                + "example: 02/08/2019 14:30\n", e.getMessage()));
        } else {
            return (e.getMessage());
        }
    }
}
