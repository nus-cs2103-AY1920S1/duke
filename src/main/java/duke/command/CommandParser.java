package duke.command;

import java.util.Arrays;

/**
 * Abstraction of a command parser responsible for resolving user input.
 */
public abstract class CommandParser {

    /**
     * Parses an input string and returns a command.
     *
     * @param commandString The input command string to parse.
     * @return The executable command.
     * @throws DukeInvalidCommandException If parsed command type is invalid.
     */
    public static Command parseCommand(String commandString)
            throws DukeInvalidCommandException {

        String[] inputs = commandString.split("\\s+");

        assert inputs != null : "Attempted command construction with null arguments.";
        Commands commandType = getCommandType(inputs);

        assert inputs.length > 0 : "Invalid number of input tokens after getting command type.";
        inputs = Arrays.copyOfRange(inputs, 1, inputs.length);

        Command command;

        switch (commandType) {
        case BYE:
            command = new ByeCommand(inputs);
            break;
        case LIST:
            command = new ListCommand(inputs);
            break;
        case FIND:
            command = new FindCommand(commandString, inputs);
            break;
        case DONE:
            command = new DoneCommand(inputs);
            break;
        case DELETE:
            command = new DeleteCommand(inputs);
            break;
        case TODO:
            command = new TodoCommand(inputs);
            break;
        case DEADLINE:
            command = new DeadlineCommand(inputs);
            break;
        case EVENT:
            command = new EventCommand(inputs);
            break;
        case ARCHIVE:
            command = new ArchiveCommand(inputs);
            break;
        default:
            //covered in try catch above with enums, only cause will be no implementation
            throw new DukeInvalidCommandException(
                    String.format(
                            "Encountered unimplemented or uncovered command type %s",
                            commandType.toString()),
                    " =X  OOPS!!! I'm sorry, but I haven't implemented it =(");
        }

        return command;
    }

    /**
     * Retrieves the command type of input string array.
     *
     * @param inputs Input string array to parse.
     * @return Commands enum command type.
     * @throws DukeInvalidCommandException If input string array does not represent a valid
     *     valid command type or array length is 0.
     */
    private static Commands getCommandType(String[] inputs) throws DukeInvalidCommandException {
        Commands commandType;
        try {
            commandType = Commands.valueOf(inputs[0].toUpperCase());
        } catch (IllegalArgumentException | IndexOutOfBoundsException ex) {
            throw new DukeInvalidCommandException(
                    " =X  OOPS!!! I'm sorry, but I don't know what that means =(");
        }
        return commandType;
    }
}
