package duke.parser;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.command.UnknownCommand;
import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static duke.task.Task.DATE_TIME_FORMATTER;

public class Parser {
    // Parser is for static use only
    private Parser() {
    }

    private static LocalDateTime parseDateTime(final String dateTime) throws DukeException {
        try {
            return LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException("Failed to parse date time: " + e.getMessage());
        }
    }

    private static Integer parseTaskNumber(final String taskNumber) throws DukeException {
        try {
            return Integer.parseInt(taskNumber) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Failed to parse task number: " + e.getMessage());
        }
    }

    /**
     * Parses input to construct a Command.
     *
     * @param input the input String to parse
     * @return the constructed Command
     * @throws DukeException if an parsing error occurs
     */
    public static Command parse(final String input) throws DukeException {
        String[] tokens = input.trim().split("\\s+", 2);
        String command = tokens[0].toLowerCase();
        // If tokens.length == 1, there are no arguments
        String arguments = (tokens.length == 1) ? "" : tokens[1];
        switch (command) {
            case "list":
                return new ListCommand();
            case "bye":
                return new ByeCommand();
            case "todo":
                return new TodoCommand(arguments);
            case "event": {
                String[] toks = arguments.split("\\s*/at\\s*", 2);
                return new EventCommand(toks[0], toks.length == 1 ? null : parseDateTime(toks[1]));
            }
            case "deadline": {
                String[] toks = arguments.split("\\s*/by\\s*", 2);
                return new DeadlineCommand(toks[0], toks.length == 1 ? null : parseDateTime(toks[1]));
            }
            case "done":
                return new DoneCommand(parseTaskNumber(arguments));
            case "delete":
                return new DeleteCommand(parseTaskNumber(arguments));
            case "find":
                return new FindCommand(arguments.isBlank() ? null : arguments.split("\\s+"));
            default:
                return new UnknownCommand();
        }
    }
}
