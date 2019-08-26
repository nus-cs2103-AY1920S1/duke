package duke.command;

import java.util.Arrays;

/**
 * Abstraction of a command parser responsible for resolving user input.
 */
public abstract class CommandParser {

    public static Command parseCommand(String commandString)
            throws DukeInvalidCommandException {

        String[] inputs = commandString.split("\\s+");
        Commands commandType;
        Command command = null;

        try {
            commandType = Commands.valueOf(inputs[0]);
            inputs = Arrays.copyOfRange(inputs, 1, inputs.length);
        } catch (IllegalArgumentException | IndexOutOfBoundsException ex) {
            throw new DukeInvalidCommandException(
                    " \u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        switch (commandType) {
            case bye:
                command = new ByeCommand(inputs);
                break;
            case list:
                command = new ListCommand(inputs);
                break;
            case done:
                command = new DoneCommand(inputs);
                break;
            case delete:
                command = new DeleteCommand(inputs);
                break;
            case todo:
                command = new TodoCommand(inputs);
                break;
            case deadline:
                command = new DeadlineCommand(inputs);
                break;
            case event:
                command = new EventCommand(inputs);
                break;
            default:
                //covered in try catch above with enums
        }

        return command;
    }
}
