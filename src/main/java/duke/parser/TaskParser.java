package duke.parser;

import duke.exception.DukeException;
import duke.exception.InvalidInputDukeException;
import duke.exception.UnknownCommandDukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.util.DateFormatter;
import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles task parsing.
 */
public class TaskParser {
    /** Generic syntax error message prefix. **/
    private static final String SYNTAX_PREFIX = "Syntax error.\nFormat: ";

    /** Syntax for deadline. **/
    private static final Pattern DEADLINE_PATTERN = Pattern.compile("deadline (.*) /by (.*)");
    private static final String DEADLINE_SYNTAX = "deadline [description] /by [dd/MM/yyyy HHmm]";
    private static final String DEADLINE_SYNTAX_MSG = SYNTAX_PREFIX + DEADLINE_SYNTAX;

    /** Syntax for event. **/
    private static final Pattern EVENT_PATTERN = Pattern.compile("event (.*) /at (.*)");
    private static final String EVENT_SYNTAX = "event [description] /at [dd/MM/yyyy HHmm]";
    private static final String EVENT_SYNTAX_MSG = SYNTAX_PREFIX + EVENT_SYNTAX;

    /**
     * Parses a given input to generate a Task.
     * If the input is unrecognised, null is returned.
     *
     * @param input Input string.
     * @return Task object.
     * @throws DukeException If input is invalid.
     */
    public static Task parse(String input) throws DukeException {
        String[] subArgs = input.split("\\s+", 2);

        Matcher matcher;
        Date date;

        switch (subArgs[0]) {
        case "todo":
            if (subArgs.length < 2 || subArgs[1].isEmpty()) {
                throw new InvalidInputDukeException("Todo description cannot be empty");
            }

            return new Todo(subArgs[1], false);
        case "deadline":
            matcher = DEADLINE_PATTERN.matcher(input);

            if (!matcher.matches()) {
                throw new InvalidInputDukeException(DEADLINE_SYNTAX_MSG);
            }

            try {
                date = DateFormatter.parse(matcher.group(2));
            } catch (ParseException e) {
                throw new InvalidInputDukeException(DEADLINE_SYNTAX_MSG);
            }

            return new Deadline(matcher.group(1), date, false);
        case "event":
            matcher = EVENT_PATTERN.matcher(input);

            if (!matcher.matches()) {
                throw new InvalidInputDukeException(EVENT_SYNTAX_MSG);
            }

            try {
                date = DateFormatter.parse(matcher.group(2));
            } catch (ParseException e) {
                throw new InvalidInputDukeException(EVENT_SYNTAX_MSG);
            }

            return new Event(matcher.group(1), date, false);
        default:
            throw new UnknownCommandDukeException();
        }
    }
}
