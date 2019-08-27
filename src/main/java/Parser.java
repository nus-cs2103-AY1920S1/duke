import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Parser {

    private String str;

    public Parser(String str) {
        this.str = str;
    }

    public String[] parse() throws DukeException, ParseException {
        String[] wordArray = str.split(" ");
        String taskWithoutType = str.replace(wordArray[0], "").trim();
        String[] parsedTask = new String[3];
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy, hh:mm a");
        parsedTask[0] = wordArray[0];
        String[] taskType = {"todo", "event", "deadline", "done", "delete"};
        if (taskWithoutType.isEmpty() && Arrays.asList(taskType).contains(parsedTask[0])) {
            throw new DukeException();
        }
        switch (wordArray[0]) {
        case "todo":
            parsedTask[1] = taskWithoutType;
            break;
        case "event":
            String[] arrOfWordsEvent = taskWithoutType.split(" /at ");
            Date parsedEventTime = format.parse(arrOfWordsEvent[1]);
            parsedTask[1] = arrOfWordsEvent[0];
            parsedTask[2] = formatter.format(parsedEventTime);
            break;
        case "deadline":
            String[] arrOfWordsDeadline = taskWithoutType.split(" /by ");
            Date parsedDeadline = format.parse(arrOfWordsDeadline[1]);
            parsedTask[1] = arrOfWordsDeadline[0];
            parsedTask[2] = formatter.format(parsedDeadline);
            break;
        case "done":
        case "delete":
            parsedTask[1] = wordArray[1];
            break;
        case "list":
            break;
        default:
            throw new IllegalArgumentException();
        }
        return parsedTask;
    }
}
