package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.DoneCommand;
import duke.command.Command;


import java.time.DateTimeException;
import java.util.InputMismatchException;

/**
 * Represents a parser object which takes in user input and changes them into command for duke to understand.
 * @see Command
 */
public class Parser {
    String stringCommand;

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
    public Command parse(String stringCommand) throws InputMismatchException, DateTimeException {
        this.stringCommand = stringCommand;

        String[] commandSplitBySpaces = stringCommand.split(" ");
        switch(commandSplitBySpaces[0]) {
            case "bye":
                return createByeCommand(commandSplitBySpaces);
            case "list":
                return createListCommand(commandSplitBySpaces);
            case "done":
                return createDoneCommand(commandSplitBySpaces);
            case "find":
                return createFindCommand(commandSplitBySpaces);
            case "delete":
                return createDeleteCommand(commandSplitBySpaces);
            case "deadline":
            case "event":
            case "todo":
                return createAddCommand(commandSplitBySpaces);
            default:
                throw new InputMismatchException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private Command createListCommand(String[] commandSplitBySpaces) throws InputMismatchException{
        if(commandSplitBySpaces.length != 1) {
            throw new InputMismatchException("I'm sorry, are you trying to call \"list\"?");
        }
        return new ListCommand(commandSplitBySpaces);
    }

    private Command createByeCommand(String[] commandSplitBySpaces) throws InputMismatchException{
        if(commandSplitBySpaces.length != 1) {
            throw new InputMismatchException("I'm sorry, are you trying to call \"bye\"?");
        }
        return new ByeCommand(commandSplitBySpaces);
    }

    private Command createDoneCommand(String[] commandSplitBySpaces) throws InputMismatchException{
        if (commandSplitBySpaces.length != 2) {
            throw new InputMismatchException("I'm sorry, are you trying to call \"done \\number\" ?");
        }
        return new DoneCommand(commandSplitBySpaces);
    }

    private Command createDeleteCommand(String[] commandSplitBySpaces) throws InputMismatchException{
        if (commandSplitBySpaces.length != 2) {
            throw new InputMismatchException("I'm sorry, are you trying to call \"delete \\number\" ?");
        }
        return new DeleteCommand(commandSplitBySpaces);
    }

    private Command createFindCommand(String[] commandSplitBySpaces) throws InputMismatchException{
        if (commandSplitBySpaces.length != 2) {
            throw new InputMismatchException("I'm sorry, are you trying to call \"find \\number\" ?");
        }
        return new FindCommand(commandSplitBySpaces);
    }

    private Command createAddCommand(String[] commandSplitBySpaces) throws InputMismatchException{
        if (commandSplitBySpaces.length == 1) {
            throw new InputMismatchException("The description cannot be empty");
        }

        String[] detail;
        switch (commandSplitBySpaces[0]) {
            case "deadline":
                String commandWithoutDeadline = stringCommand.substring("deadline".length()).trim();
                detail = commandWithoutDeadline.split(" /by ");
                return new AddCommand("deadline", detail);
            case "todo":
                return new AddCommand("todo", commandSplitBySpaces);
            case "event":
                String commandWithoutEvent = stringCommand.substring("event".length()).trim();
                detail = commandWithoutEvent.split(" /at ");
                return new AddCommand("event", detail);
        }
        return null;
    }
}
