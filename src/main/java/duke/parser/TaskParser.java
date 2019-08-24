package duke.parser;

import duke.exception.DukeException;
import duke.exception.InvalidInputDukeException;
import duke.exception.InvalidTaskDukeException;
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

public class TaskParser {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("d/M/yyyy HHmm");
    private static final Pattern DEADLINE_PATTERN = Pattern.compile("(.*) /by (.*)");
    private static final Pattern EVENT_PATTERN = Pattern.compile("(.*) /at (.*)");

    public static Task parse(String input) throws DukeException {
        String[] subArgs = input.split("\\s+", 2);

        Matcher matcher;
        Date date;

        switch (subArgs[0]) {
        case "todo":
            if (subArgs[1].isEmpty()) {
                throw new InvalidInputDukeException("Todo description cannot be empty");
            }

            return new Todo(subArgs[1]);
        case "deadline":
            matcher = DEADLINE_PATTERN.matcher(subArgs[1]);

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
            matcher = EVENT_PATTERN.matcher(subArgs[1]);

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
