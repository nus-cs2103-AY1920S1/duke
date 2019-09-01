package duke;

import duke.command.*;

import java.util.InputMismatchException;

/**
 * Represents a parser object which takes in user input and changes them into command for duke to understand.
 * @see Command
 */
public class Parser {

    /**
     * Default constructor for duke.Parser.
     */
    public Parser() {
    }

    /**
     * Converts a string given by user into actual command to be executed.
     * @param stringCommand String representation of the user input
     * @return duke.command.Command which would be executed
     * @throws InputMismatchException duke.command.Command is not recognised
     */
    public Command parse(String stringCommand) throws InputMismatchException {
        if (stringCommand.equalsIgnoreCase("bye")) {
            return new ByeCommand(stringCommand);
        } else if (stringCommand.equalsIgnoreCase("list")) {
            return new ListCommand(stringCommand);
        } else {
            String[] commandSplit = stringCommand.split(" ");
            String deadline = "deadline";
            String event = "event";
            String todo = "todo";
            if (commandSplit[0].equalsIgnoreCase("done")) {
                return new DoneCommand(stringCommand);
            } else if (commandSplit[0].equalsIgnoreCase("find")) {
                return new FindCommand(stringCommand);
            } else if (commandSplit[0].equalsIgnoreCase("delete")) {
                return new DeleteCommand(stringCommand);
            } else if (commandSplit[0].equalsIgnoreCase(deadline) || commandSplit[0].equalsIgnoreCase(event)
                    || commandSplit[0].equalsIgnoreCase(todo)) {
                return new AddCommand(stringCommand);
            } else {
                throw new InputMismatchException("I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
