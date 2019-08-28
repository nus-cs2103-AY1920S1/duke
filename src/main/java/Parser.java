import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Arrays;

public class Parser {

    public static Command parseCommand(String fullCommand) {
        String[] arr = fullCommand.split(" ");
        // System.out.println(Arrays.toString(arr));
        try {
            switch (arr[0]) {
            case "list":
                return new ListCommand();
            case "done":
                String doneLine = fullCommand.substring(5);
                return new DoneCommand(doneLine);
            case "todo":
                String todoLine = fullCommand.substring(5);
                return new AddTodoCommand(todoLine);
            case "deadline":
                String deadlineLine = fullCommand.substring(9);
                return new AddDeadlineCommand(deadlineLine);
            case "event":
                String eventLine = fullCommand.substring(6);
                return new AddEventCommand(eventLine);
            case "delete":
                String deleteLine = fullCommand.substring(7);
                return new DeleteCommand(deleteLine);
            case "bye":
                return new ExitCommand();
            default:
                throw new InvalidArgumentException();
            }
        } catch (DukeException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public static Task parseLine(String line) {
        String[] arr = line.split(",");
        boolean isDone = arr[2].equals("true");
        switch (arr[0]) {
        case "todo":
            return new Todo(arr[1], isDone);
        case "deadline":
            return new Deadline(arr[1], parseDate(arr[3]), isDone);
        case "event":
            return new Event(arr[1], parseDate(arr[3]), isDone);
        default:
            throw new DukeException("Parsing error");
        }
    }

    public static String parseTask(Task task) {
        return task.encode();
    }

    public static Date parseDate(String str) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy HHmm").parse(str);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public static String format(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy HHmm").format(date);
    }

}
