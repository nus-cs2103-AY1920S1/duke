package dukegui.module;

import duke.exception.DukeIllegalCommandException;

import duke.module.AutoResponse;

import dukegui.command.Command;
import dukegui.command.AddDeadlineCommand;
import dukegui.command.AddEventCommand;
import dukegui.command.AddTodoCommand;
import dukegui.command.DeleteCommand;
import dukegui.command.DoneCommand;
import dukegui.command.ExitCommand;
import dukegui.command.FindCommand;
import dukegui.command.ListCommand;

/**
 * Parses various Strings into meaningful information.
 */
public class Parser {

    /**
     * This stores all the commands that Duke can understand and carry out.
     */
    private enum CommandType {
        LIST,
        DONE,
        FIND,
        TODO,
        EVENT,
        DEADLINE,
        DELETE,
        BYE;
    }

    /**
     * Parses the inputted command into a {@link Command} object.
     *
     * @param command User inputted string to be parsed into a {@link Command} object.
     * @param description The rest of the user input;
     * @return The {@link Command} object corresponding to the user input.
     * @throws DukeIllegalCommandException When the string inputted is not a valid {@link CommandType}.
     */
    public static Command parseToCommand(String command, String description) throws DukeIllegalCommandException {
        try {
            switch (CommandType.valueOf(command.toUpperCase())) {
            case LIST:
                return new ListCommand();
            case DONE:
                return new DoneCommand(description);
            case FIND:
                return new FindCommand(description);
            case TODO:
                return new AddTodoCommand(description);
            case EVENT:
                return new AddEventCommand(description);
            case DEADLINE:
                return new AddDeadlineCommand(description);
            case DELETE:
                return new DeleteCommand(description);
            case BYE:
                return new ExitCommand();
            default:
                throw new DukeIllegalCommandException(AutoResponse.ERROR_ILLEGAL_COMMAND);
            }
        } catch (IllegalArgumentException e) {
            throw new DukeIllegalCommandException(AutoResponse.ERROR_ILLEGAL_COMMAND);
        }
    }

}
