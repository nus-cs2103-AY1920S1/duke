import java.lang.IllegalArgumentException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

class Parser {
    private static final String emptyToDoErrorMessage = "____________________________________________________________\n"
            + "\u2639 OOPS!!! The description of a todo cannot be empty.\n"
            + "____________________________________________________________";// Split by space, then check for /by
    private static final String illegalArgumentMessage = "____________________________________________________________\n"
            + "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\n"
            + "____________________________________________________________";

    public static Hashtable<String, String> parse(String toDo) throws IllegalArgumentException {
        String task, date, index;
        SimpleDateFormat readFormat;

        Hashtable<String, String> result = new Hashtable<>();
        String[] arr = toDo.split(" ", 2);
        String command = arr[0];
        result.put("command", command);

        switch (command) {
        case "list":
        case "bye":
            break;
        case "todo":
            if (arr.length == 2) {
                task = arr[1];
                result.put("task", task);
            } else {
                throw new IllegalArgumentException(emptyToDoErrorMessage);
            }
            break;
        case "deadline":
            task = arr[1].split("/by")[0].trim();
            date = arr[1].split("/by")[1].trim();
            result.put("task", task);
            result.put("date", date);
            break;
        case "event":
            task = arr[1].split("/at")[0].trim();
            date = arr[1].split("/at")[1].trim();
            result.put("task", task);
            result.put("date", date);
            break;
        case "done":
        case "delete":
            index = arr[1];
            result.put("index", index);
            break;
        case "save":
            result.put("path", "./Data/duke.txt");
            break;
        default:
            throw new IllegalArgumentException(illegalArgumentMessage);
        }

        return result;
    }
}