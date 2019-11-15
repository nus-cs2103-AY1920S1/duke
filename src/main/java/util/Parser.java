package util;

import command.*;
import service.Duke;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import util.exception.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

public class Parser {
    private static HashSet<String> keywords;
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
    private static final String INVALID_COMMAND = "对不起， 我听不懂☹";

    static {
        keywords = new HashSet<>();
        keywords.add("bye");
        keywords.add("done");
        keywords.add("todo");
        keywords.add("event");
        keywords.add("deadline");
        keywords.add("list");
        keywords.add("delete");
        keywords.add("find");
        keywords.add("update");
    }

    public static Command parse(String args) {
        String[] spaceSeparatedStrings = args.trim().split("\\s+");
        return parse(spaceSeparatedStrings);
    }

    private static Command parse(String[] args) throws DukeException {
        if (!keywords.contains(args[0])) {
            throw new DukeException(INVALID_COMMAND);
        }

        assert keywords.contains(args[0]);

        switch (args[0]) {
        case "delete":
            return new DeleteCommand(Integer.parseInt(args[1]));
        case "bye":
            return new ExitCommand();
        case "find":
            return new FindCommand(joinArguments(args));
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(Integer.parseInt(args[1]));
        case "update":
            Task newTask = Parser.parseTask(sliceArguments(args, 2));
            return new UpdateCommand(Integer.parseInt(args[1]),newTask);
        default:
            return new AddCommand(parseTask(args));
        }
    }

    private static String[] sliceArguments(String[] args) {
        return sliceArguments(args, 1);
    }

    private static String[] sliceArguments(String[] args, int n) {
        assert args.length > n;
        return Arrays.copyOfRange(args, n, args.length);
    }

    private static String joinArguments(String[] args) {
        assert args.length > 1;
        return String.join(" ", sliceArguments(args));
    }

    private static Task parseTask(String[] args) {
        String[] description = sliceArguments(args);
        try {
            switch (args[0]) {
            case "todo":
                if (description.length == 0) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                return new Todo(description);
            case "deadline": {
                String[] details = String.join(" ", description).split("/by");
                if (details.length == 1) {
                    throw new DukeException("OOPS!!! The deadline of a deadline cannot be empty.");
                }
                return new Deadline(details[0].trim(), formatter.parse(details[1].trim()));
            }
            case "event": {
                String[] details = String.join(" ", description).split("/at");
                if (details.length == 1) {
                    throw new DukeException("OOPS!!! The duration of an event cannot be empty.");
                }

                String[] dates = details[1].trim().split("-");
                if (dates.length < 2) {
                    throw new DukeException("OOPS!!! Events must have a start and end date.");
                }
                Date startPeriod = formatter.parse(dates[0].trim());
                Date endPeriod = formatter.parse(dates[1].trim());
                return new Event(details[0].trim(), startPeriod, endPeriod);
            }
            default:
            }
        } catch (ParseException e) {
            throw new DukeException("Dates must follow dd/MM/yyyy HHmm format!");
        }
        return null;
    }
}
