package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.exception.InvalidInputDukeException;
import duke.exception.UnknownCommandDukeException;
import duke.task.Task;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles command parsing.
 */
public class CommandParser {
    private static final Pattern DONE_PATTERN = Pattern.compile("done (\\d+$)");
    private static final Pattern DELETE_PATTERN = Pattern.compile("delete (\\d+$)");
    private static final Pattern FIND_PATTERN = Pattern.compile("find (.*)");

    /**
     * Parses a index. It must be a positive integer.
     *
     * @param input Input containing the number
     * @return Index
     * @throws DukeException If the result could not be parsed
     */
    private static int parseIndex(String input) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(input);

            if (index <= 0) {
                throw new NumberFormatException();
            }

            index--;
        } catch (NumberFormatException e) {
            throw new InvalidInputDukeException("Invalid task number entered.");
        }

        return index;
    }

    /**
     * Parses a given input to generate a Command.
     * If the input is unrecognised, null is returned.
     *
     * @param input Input string.
     * @return Command to be executed.
     * @throws DukeException If input is invalid.
     */
    public static Command parse(String input) throws DukeException {
        if (input.isEmpty()) {
            throw new UnknownCommandDukeException();
        }

        Matcher matcher;
        String[] tokens = input.split("\\s+", 2);

        switch (tokens[0]) {
        case "bye":
            return new ExitCommand();
        case "help":
            return new HelpCommand();
        case "list":
            return new ListCommand();
        case "done":
            matcher = DONE_PATTERN.matcher(input);

            if (!matcher.matches()) {
                throw new InvalidInputDukeException("Syntax error. "
                        + "Usage: done [task number from 1 onwards]");
            }

            return new DoneCommand(parseIndex(matcher.group(1)));
        case "delete":
            matcher = DELETE_PATTERN.matcher(input);

            if (!matcher.matches()) {
                throw new InvalidInputDukeException("Syntax error. "
                        + "Usage: delete [task number from 1 onwards]");
            }

            return new DeleteCommand(parseIndex(matcher.group(1)));
        case "find":
            matcher = FIND_PATTERN.matcher(input);

            if (!matcher.matches()) {
                throw new InvalidInputDukeException("Syntax error. "
                        + "Usage: find [keywords]");
            }

            return new FindCommand(matcher.group(1));
        default:
            Task task = TaskParser.parse(input);
            return new AddCommand(task);
        }
    }
}
