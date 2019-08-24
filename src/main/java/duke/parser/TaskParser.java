package duke.parser;

import duke.exception.DukeException;
import duke.exception.InvalidInputDukeException;
import duke.exception.UnknownCommandDukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles task parsing.
 */
public class TaskParser {
    /** Syntax for date. **/
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("d/M/yyyy HHmm");

    /** Syntax for deadline. **/
    private static final Pattern DEADLINE_PATTERN = Pattern.compile("deadline (.*) /by (.*)");

    /** Syntax for event. **/
    private static final Pattern EVENT_PATTERN = Pattern.compile("event (.*) /at (.*)");

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

            return new Todo(subArgs[1]);
        case "deadline":
            matcher = DEADLINE_PATTERN.matcher(input);

            if (!matcher.matches()) {
                throw new InvalidInputDukeException("Syntax error. "
                        + "Deadline should be: deadline [description] /at [d/M/yyyy HHmm]");
            }

            try {
                date = DATE_FORMAT.parse(matcher.group(2));
            } catch (ParseException e) {
                throw new InvalidInputDukeException("Syntax error. Date should be: d/M/yyyy HHmm");
            }

            return new Deadline(matcher.group(1), date);
        case "event":
            matcher = EVENT_PATTERN.matcher(input);

            if (!matcher.matches()) {
                throw new InvalidInputDukeException("Syntax error. "
                        + "Event should be: event [description] /at [d/M/yyyy HHmm]");
            }

            try {
                date = DATE_FORMAT.parse(matcher.group(2));
            } catch (ParseException e) {
                throw new InvalidInputDukeException("Syntax error. Date should be: d/M/yyyy HHmm");
            }

            return new Event(matcher.group(1), date);
        default:
            throw new UnknownCommandDukeException();
        }
    }
}
