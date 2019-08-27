import java.lang.IllegalArgumentException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

// Command: Strictly takes in specified things; same for task list
// Parser's role: Split the string up; create the tasks too
// TODO: handle illegal commands and empty to do Command, and hard coded command initialisation

/**
 * Parser has one method: parse.
 * Parsing breaks down the input full command string, and outputs its associated command.
 * Parsing detects and reports any syntax errors with the full command.
 */
class Parser {
    private static final String emptyToDoErrorMessage = "____________________________________________________________\n"
            + "\u2639 OOPS!!! The description of a todo cannot be empty.\n"
            + "____________________________________________________________";// Split by space, then check for /by

    public static Command parse(String fullCommand) throws IllegalArgumentException, ParseException {
        String task;
        int i;
        Date date;
        String format = "dd/MM/yyyy HHmm";
        SimpleDateFormat readFormat = new SimpleDateFormat(format);;
        Command command;

//        Hashtable<String, String> result = new Hashtable<>();
        String[] arr = fullCommand.split(" ", 2);
        String commandType = arr[0];
//        result.put("command", command);

        switch (commandType) {
        case "list":
            command = new ListCommand();
            break;
        case "bye":
            command = new ByeCommand();
            break;
        case "todo":
            if (arr.length == 2) {
                command = new ToDoCommand(new ToDo(arr[1]));
            } else {
                throw new IllegalArgumentException(emptyToDoErrorMessage);
            }
            break;
        case "deadline":
            task = arr[1].split("/by")[0].trim();
            date = readFormat.parse(arr[1].split("/by")[1].trim());
            command = new DeadlineCommand(new Deadline(task, date));
            break;
        case "event":
            task = arr[1].split("/at")[0].trim();
            date = readFormat.parse(arr[1].split("/at")[1].trim());
            command = new EventCommand(new Event(task, date));
            break;
        case "done":
            i = Integer.parseInt(arr[1]);
            command = new DoneCommand(i);
            break;
        case "delete":
            i = Integer.parseInt(arr[1]);
            command = new DeleteCommand(i);
            break;
        case "save":
            command = new SaveCommand();
            break;
        default:
            command = new IllegalCommand();
        }
        return command;
    }
}