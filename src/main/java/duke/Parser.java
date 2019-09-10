package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Parser class takes in a string, parses it to the required
 * task format before returning it.
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
     * Returns a Task that has been formatted accordingly.
     *
     * @return task Task that has been formatted.
     * @throws DukeException If task is empty.
     * @throws ParseException If date or time of task does not follow the format.
     */
    public Task parse() throws DukeException, ParseException {
        Task task = null;
        String[] wordArray = str.split(" ");
        String taskWithoutType = str.replace(wordArray[0], "").trim();

        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");

        if (taskWithoutType.isEmpty()) {
            throw new DukeException();
        }

        switch (wordArray[0]) {
        case "todo":
            task = new ToDo(taskWithoutType);
            break;
        case "event":
            String[] arrOfWordsEvent = taskWithoutType.split(" /at ");
            Date parsedEventTime = dateTimeFormat.parse(arrOfWordsEvent[1]);
            task = new Event(arrOfWordsEvent[0], parsedEventTime);
            break;
        case "deadline":
            String[] arrOfWordsDeadline = taskWithoutType.split(" /by ");
            Date parsedDeadline = dateTimeFormat.parse(arrOfWordsDeadline[1]);
            task = new Deadline(arrOfWordsDeadline[0], parsedDeadline);
            break;
        }
        return task;
    }
}
