package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
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
    private static int getIndexFromMatcher(String matcherResult) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(matcherResult);

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

        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("done")) {
            Pattern donePattern = Pattern.compile("done (\\d+$)");
            matcher = donePattern.matcher(input);

            if (!matcher.matches()) {
                throw new InvalidInputDukeException("Syntax error. "
                        + "Usage: done [task number from 1 onwards]");
            }

            return new DoneCommand(getIndexFromMatcher(matcher.group(1)));
        } else if (input.startsWith("delete")) {
            Pattern deletePattern = Pattern.compile("delete (\\d+$)");
            matcher = deletePattern.matcher(input);

            if (!matcher.matches()) {
                throw new InvalidInputDukeException("Syntax error. "
                        + "Usage: delete [task number from 1 onwards]");
            }

            return new DeleteCommand(getIndexFromMatcher(matcher.group(1)));
        } else if (input.startsWith("find")) {
            Pattern findPattern = Pattern.compile("find (.*)");
            matcher = findPattern.matcher(input);

            if (!matcher.matches()) {
                throw new InvalidInputDukeException("Syntax error. "
                        + "Usage: find [keywords]");
            }

            return new FindCommand(matcher.group(1));
        } else {
            Task task = TaskParser.parse(input);
            return new AddCommand(task);
        }
    }
}
