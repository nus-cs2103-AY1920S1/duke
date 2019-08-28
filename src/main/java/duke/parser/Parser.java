package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.util.DateUtil;

import java.util.Date;

/**
 * Represents a parser that parses strings and creates commands.
 */
public class Parser {

    /**
     * Parses the command contained in a {@link String} and returns a {@link Command} that represents it.
     *
     * @param fullCommand {@link String} containing the command.
     * @return {@link Command} representing the command.
     * @throws DukeException if the command has parser errors.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] arr = fullCommand.trim().split(" ", 2);
        String commandWord = arr[0];

        try {
            switch (commandWord) {
                case DeadlineCommand.COMMAND_WORD:
                    return prepareDeadline(arr[1]);

                case EventCommand.COMMAND_WORD:
                    return prepareEvent(arr[1]);

                case ToDoCommand.COMMAND_WORD:
                    return prepareToDo(arr[1]);

                case DeleteCommand.COMMAND_WORD:
                    return prepareDelete(arr[1]);

                case ListCommand.COMMAND_WORD:
                    return new ListCommand();

                case DoneCommand.COMMAND_WORD:
                    return prepareDone(arr[1]);

                case ByeCommand.COMMAND_WORD:
                    return new ByeCommand();

                default:
                    return prepareIncorrect();
            }
        } catch (IndexOutOfBoundsException e) {
            return prepareIncorrect();
        }
    }

    private static Command prepareToDo(String args) {
        String description = args;
        return new ToDoCommand(description);
    }

    private static Command prepareDeadline(String args) throws DukeException {
        try {
            String[] params = args.split(" /by ");
            String description = params[0];
            Date by = DateUtil.parse(params[1]);
            return new DeadlineCommand(description, by);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("An error occurred while creating a Deadline: Insufficient parameters");
        }
    }

    private static Command prepareEvent(String args) throws DukeException {
        try {
            String[] params = args.split(" /at ");
            String description = params[0];
            Date at = DateUtil.parse(params[1]);
            return new EventCommand(description, at);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("An error occurred while creating an Event: Insufficient parameters");
        }
    }

    private static Command prepareDelete(String args) {
        int id = Integer.parseInt(args) - 1;
        return new DeleteCommand(id);
    }

    private static Command prepareDone(String args) {
        int id = Integer.parseInt(args) - 1;
        return new DoneCommand(id);
    }

    private static Command prepareIncorrect() {
        return new IncorrectCommand();
    }
}
