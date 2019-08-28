import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

class Parser {
    private static HashSet<String> keywords;
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

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
    }

    static Command parse(String[] args) throws DukeException {
        if (!keywords.contains(args[0])) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        switch (args[0]) {
        case "delete":
            return new DeleteCommand(Integer.parseInt(args[1]));
        case "bye":
            return new ExitCommand();
        case "find":
            return new FindCommand(getArguments(args));
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(Integer.parseInt(args[1]));
        default:
            return new AddCommand(parseTask(args));
        }
    }

    private static String getArguments(String[] args) {
        return String.join(" ", Arrays.copyOfRange(args, 1, args.length));
    }

    private static Task parseTask(String[] args) {
        String[] description = Arrays.copyOfRange(args, 1, args.length);
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
                Date startPeriod = formatter.parse(dates[0].trim());
                Date endPeriod = formatter.parse(dates[1].trim());
                return new Event(details[0].trim(), startPeriod, endPeriod);
            }
            default:
            }
        } catch (ParseException e) {
            throw new DukeException("Dates must have proper format!");
        }
        return null;
    }
}
