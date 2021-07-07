package duke.parser;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.command.UndoCommand;
import duke.exception.DukeException;
import duke.shared.Messages;

import java.util.Arrays;

/**
 * Parses command.
 */
public class Parser {
    /**
     * Converts the command into command object.
     *
     * @param command command inputted by the user.
     * @return converted command object.
     * @throws DukeException throws by {@link #findCommand(String[])}.
     * @throws NumberFormatException throws by {@link #findCommand(String[])}.
     */
    public static Command parse(String command) throws DukeException, NumberFormatException {
        String[] commandArr = command.split("\\s+");
        return findCommand(commandArr);
    }

    /**
     * Returns the correct command object based on user inputted command.
     *
     * @param commands is the last command entered by the user.
     * @throws DukeException if user inputted an invalid command.
     * @throws NumberFormatException if user inputted an invalid description for done and delete command.
     */
    public static Command findCommand(String[] commands) throws DukeException, NumberFormatException {
        Command command;
        switch (commands[0]) {
        case "bye":
            command = verifyByeCommand(commands);
            break;
        case "list":
            command = verifyListCommand(commands);
            break;
        case "done":
            command = verifyDoneCommand(commands);
            break;
        case "todo":
            command = verifyTodoCommand(commands);
            break;
        case "deadline":
            command = verifyDeadlineCommand(commands);
            break;
        case "event":
            command = verifyEventCommand(commands);
            break;
        case "delete":
            command = verifyDeleteCommand(commands);
            break;
        case "find":
            command = verifyFindCommand(commands);
            break;
        case "undo":
            command = verifyUndoCommand(commands);
            break;
        default:
            throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
        }
        return command;
    }

    /**
     * Verifies bye command.
     *
     * @param commands is the command enter by the user.
     * @return ExitCommand if the command matches the syntax.
     * @throws DukeException if user inputted an invalid command.
     */
    public static Command verifyByeCommand(String[] commands) throws DukeException {
        if (commands.length == 1) {
            return new ExitCommand();
        } else {
            throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
        }
    }

    /**
     * Verifies list command.
     *
     * @param commands is the command enter by the user.
     * @return ListCommand if the command matches the syntax.
     * @throws DukeException if user inputted an invalid command.
     */
    public static Command verifyListCommand(String[] commands) throws DukeException {
        if (commands.length == 1) {
            return new ListCommand();
        } else {
            throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
        }
    }

    /**
     * Verifies done command.
     *
     * @param commands is the command enter by the user.
     * @return DoneCommand if the command matches the syntax.
     * @throws DukeException if user inputted an invalid command.
     */
    public static Command verifyDoneCommand(String[] commands) throws DukeException, NumberFormatException {
        try {
            if (commands.length == 2) {
                return new DoneCommand(Integer.parseInt(commands[1]));
            } else if (commands.length == 1) {
                throw new DukeException(String.format(Messages.DESCRIPTION_MISSING_EXCEPTION, "done"));
            } else {
                throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException(String.format(Messages.DESCRIPTION_FORMAT_EXCEPTION, "done",
                    "number"));
        }
    }

    /**
     * Verifies todo command.
     *
     * @param commands is the command enter by the user.
     * @return TodoCommand if the command matches the syntax.
     * @throws DukeException if user inputted an invalid command.
     */
    public static Command verifyTodoCommand(String[] commands) throws DukeException {
        if (commands.length > 1) {
            return new ToDoCommand(commands);
        } else if (commands.length == 1) {
            throw new DukeException(String.format(Messages.DESCRIPTION_MISSING_EXCEPTION, "todo"));
        } else {
            throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
        }
    }

    /**
     * Verifies deadline command.
     *
     * @param commands is the command enter by the user.
     * @return DeadlineCommand if the command matches the syntax.
     * @throws DukeException if user inputted an invalid command.
     */
    public static Command verifyDeadlineCommand(String[] commands) throws DukeException {
        if (commands.length > 1) {
            return new DeadlineCommand(commands);
        } else if (commands.length == 1) {
            throw new DukeException(String.format(Messages.DESCRIPTION_MISSING_EXCEPTION, "deadline"));
        } else {
            throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
        }
    }

    /**
     * Verifies event command.
     *
     * @param commands is the command enter by the user.
     * @return EventCommand if the command matches the syntax.
     * @throws DukeException if user inputted an invalid command.
     */
    public static Command verifyEventCommand(String[] commands) throws DukeException {
        if (commands.length > 1) {
            return new EventCommand(commands);
        } else if (commands.length == 1) {
            throw new DukeException(String.format(Messages.DESCRIPTION_MISSING_EXCEPTION, "event"));
        } else {
            throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
        }
    }

    /**
     * Verifies delete command.
     *
     * @param commands is the command enter by the user.
     * @return DeleteCommand if the command matches the syntax.
     * @throws DukeException if user inputted an invalid command.
     */
    public static Command verifyDeleteCommand(String[] commands) throws DukeException, NumberFormatException {
        try {
            if (commands.length == 2) {
                return new DeleteCommand(Integer.parseInt(commands[1]));
            } else if (commands.length == 1) {
                throw new DukeException(String.format(Messages.DESCRIPTION_MISSING_EXCEPTION, "delete"));
            } else {
                throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException(String.format(Messages.DESCRIPTION_FORMAT_EXCEPTION, "delete",
                    "number"));
        }
    }

    /**
     * Verifies find command.
     *
     * @param commands is the command enter by the user.
     * @return FindCommand if the command matches the syntax.
     * @throws DukeException if user inputted an invalid command.
     */
    public static Command verifyFindCommand(String[] commands) throws DukeException {
        if (commands.length > 1) {
            return new FindCommand(Arrays.copyOfRange(commands, 1, commands.length));
        } else if (commands.length == 1) {
            throw new DukeException(String.format(Messages.DESCRIPTION_FORMAT_EXCEPTION, "find"));
        } else {
            throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
        }
    }

    /**
     * Verifies undo command.
     *
     * @param commands is the command enter by the user.
     * @return UndoCommand if the command matches the syntax.
     * @throws DukeException if user inputted an invalid command.
     */
    public static Command verifyUndoCommand(String[] commands) throws DukeException {
        if (commands.length == 1) {
            return new UndoCommand();
        } else {
            throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
        }
    }
}
