package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.DukeIndexOutOfBoundsException;
import duke.exception.DukeMissingDescriptionException;
import duke.exception.DukeUnknownInputException;

import java.util.Arrays;

import static duke.task.TaskType.*;

/**
 * Deals with making sense of commands.
 */
public class Parser {
    /**
     * Parses the full command and returns the corresponding Command instance.
     *
     * @param command the full command to parse.
     * @return the command corresponding to the input.
     * @throws DukeException an exception generated when parsing the input.
     */
    public static Command parse(String command) throws DukeException {
        // remove trailing/leading whitespace and split by whitespace(s)
        command = command.strip();
        String[] commands = command.split("[ ]+");
        String[] args = Arrays.copyOfRange(commands, 1, commands.length);

        assert args.length == commands.length - 1: "Incorrect array copy";

        switch (commands[0]) {
        case "todo":
            return parseTodo(args);
        case "event":
            return parseEvent(args);
        case "deadline":
            return parseDeadline(args);
        case "find":
            return parseFind(args);
        case "done":
            return parseDone(args);
        case "delete":
            return parseDelete(args);
        case "list":
            return parseList(args);
        case "bye":
            return parseBye(args);
        default:
            throw new DukeMissingDescriptionException("OOPS! I'm sorry, but I don't know what that means...");
        }
    }

    /*
    Business logic for each command. Refactor concept: Extract Method
     */
    private static Command parseTodo(String[] args) {
        if (args.length == 0) {
            throw new DukeMissingDescriptionException("OOPS! The description of a todo cannot be empty.");
        }
        return new AddCommand(TODO, args, false);
    }

    private static Command parseEvent(String[] args) {
        String[] eventArgs = String.join(" ", args).split(" /at ");
        if (eventArgs.length != 2) {
            throw new DukeUnknownInputException("Incorrect argument count for event!");
        }

        return new AddCommand(EVENT, args, false);
    }

    private static Command parseDeadline(String[] args) {
        String[] deadlineArgs = String.join(" ", args).split(" /by ");
        if (deadlineArgs.length != 2) {
            throw new DukeUnknownInputException("Incorrect argument count for deadline!");
        }
        String deadline = deadlineArgs[1];
        // enforce example format 2/12/2019 1800
        if (deadline.split(" ").length != 2
                || deadline.split(" ")[0].split("/").length != 3
                || Integer.valueOf(deadline.split(" ")[1]) < 0
                || Integer.valueOf(deadline.split(" ")[1]) > 2400) {
            throw new DukeUnknownInputException("Unknown deadline String format passed :(");
        }

        return new AddCommand(DEADLINE, args, false);
    }

    private static Command parseFind(String[] args) {
        if (args.length == 0) {
            throw new DukeMissingDescriptionException("OOPS! The keyword of a search cannot be empty.");
        }

        String keyword = args[0].strip();
        return new FindCommand(keyword, false);
    }

    private static Command parseDone(String[] args) {
        int doneIdx = Integer.valueOf(args[0]);
        if (doneIdx < 0) {
            throw new DukeIndexOutOfBoundsException("Attempting to mark task not in list!");
        }

        return new DoneCommand(doneIdx, false);
    }

    private static Command parseDelete(String[] args) {
        int deleteIdx = Integer.valueOf(args[0]);
        if (deleteIdx < 0) {
            throw new DukeIndexOutOfBoundsException("Attempting to delete task not in list!");
        }

        return new DeleteCommand(deleteIdx, false);
    }

    private static Command parseList(String[] args) {
        return new ListCommand(false);
    }

    private static Command parseBye(String[] args) {
        return new ExitCommand(true);
    }
}
