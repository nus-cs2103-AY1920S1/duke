package duke.parser;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.IncorrectCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.command.UnknownCommand;
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
        final String commandWord = arr[0];
        final String args = arr.length > 1 ? arr[1] : null;

        switch (commandWord) {
        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(args);

        case EventCommand.COMMAND_WORD:
            return prepareEvent(args);

        case ToDoCommand.COMMAND_WORD:
            return prepareToDo(args);

        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(args);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case FindCommand.COMMAND_WORD:
            return prepareFind(args);

        case DoneCommand.COMMAND_WORD:
            return prepareDone(args);

        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();

        default:
            return new UnknownCommand();
        }
    }

    private static Command prepareToDo(String args) {
        if (args == null) {
            return new IncorrectCommand(ToDoCommand.MESSAGE_USAGE);
        }
        String description = args.trim();
        return new ToDoCommand(description);
    }

    private static Command prepareDeadline(String args) throws DukeException, IllegalArgumentException {
        if (args == null) {
            return new IncorrectCommand(DeadlineCommand.MESSAGE_USAGE);
        }
        String[] params = args.trim().split(" /by ");
        if (params.length < 2) {
            return new IncorrectCommand(DeadlineCommand.MESSAGE_USAGE);
        }
        String description = params[0];
        Date by = DateUtil.parse(params[1]);
        return new DeadlineCommand(description, by);
    }

    private static Command prepareEvent(String args) throws DukeException, IllegalArgumentException {
        if (args == null) {
            return new IncorrectCommand(EventCommand.MESSAGE_USAGE);
        }
        String[] params = args.trim().split(" /at ");
        if (params.length < 2) {
            return new IncorrectCommand(EventCommand.MESSAGE_USAGE);
        }
        String description = params[0];
        Date at = DateUtil.parse(params[1]);
        return new EventCommand(description, at);
    }

    private static Command prepareDelete(String args) {
        if (args == null) {
            return new IncorrectCommand(DeleteCommand.MESSAGE_USAGE);
        }
        try {
            int id = parseOneBasedIndex(args);
            return new DeleteCommand(id);
        } catch (NumberFormatException e) {
            return new IncorrectCommand(DeleteCommand.MESSAGE_USAGE);
        }
    }

    private static Command prepareFind(String args) {
        if (args == null) {
            return new IncorrectCommand(FindCommand.MESSAGE_USAGE);
        }
        String keyword = args.trim();
        return new FindCommand(keyword);
    }

    private static Command prepareDone(String args) {
        if (args == null) {
            return new IncorrectCommand(DoneCommand.MESSAGE_USAGE);
        }
        try {
            int id = parseOneBasedIndex(args);
            return new DoneCommand(id);
        } catch (NumberFormatException e) {
            return new IncorrectCommand(DoneCommand.MESSAGE_USAGE);
        }
    }

    private static int parseOneBasedIndex(String oneBasedIndex) {
        oneBasedIndex = oneBasedIndex.trim();
        if (Integer.parseUnsignedInt(oneBasedIndex) == 0) {
            throw new NumberFormatException("oneBasedIndex cannot be 0");
        }
        return Integer.parseUnsignedInt(oneBasedIndex) - 1;
    }
}
