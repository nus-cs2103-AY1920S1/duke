package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Parser class takes in a string, parses it to the required
 * date and time format before returning it.
 */
public class Parser {

    private String str;

    /**
     * Constructor for Parser class.
     *
     * @param str String that needs to be parsed.
     */
    public Parser(String str) {
        this.str = str;
    }

    /**
     * Returns a Task with a formatted date and time.
     *
     * @return parsedTask Task that has been formatted.
     * @throws DukeException If task is empty.
     * @throws ParseException If date or time of task does not follow the format.
     */
    public Task parse() throws DukeException, ParseException {
        Task parsedTask = null;
        String[] wordArray = str.split(" ");
        String taskWithoutType = str.replace(wordArray[0], "").trim();

        SimpleDateFormat dateTimeInputFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd MMM yyyy, hh:mm a");

        if (taskWithoutType.isEmpty()) {
            throw new DukeException();
        }

        switch (wordArray[0]) {
        case "todo":
            parsedTask = new ToDo(taskWithoutType);
            break;
        case "event":
            String[] arrOfWordsEvent = taskWithoutType.split(" /at ");
            Date parsedEventTime = dateTimeInputFormat.parse(arrOfWordsEvent[1]);
            parsedTask = new Event(arrOfWordsEvent[0], dateTimeFormat.format(parsedEventTime));
            break;
        case "deadline":
            String[] arrOfWordsDeadline = taskWithoutType.split(" /by ");
            Date parsedDeadline = dateTimeInputFormat.parse(arrOfWordsDeadline[1]);
            parsedTask = new Deadline(arrOfWordsDeadline[0], dateTimeFormat.format(parsedDeadline));
            break;
        }
        return parsedTask;
    }
}
