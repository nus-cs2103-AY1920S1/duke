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
    private boolean isPriority;

    /**
     * Constructor for Parser class.
     *
     * @param str String that needs to be parsed.
     * @param p Priority level of task.
     */
    public Parser(String str, boolean p) {
        this.str = str;
        this.isPriority = p;
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
        String eventType = "";
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
        String taskDescription = "";

        if (isPriority) {
            taskDescription = str.replace(wordArray[0], "")
                                 .replace(wordArray[1], "").trim();
            eventType = wordArray[1];
        } else {
            taskDescription = str.replace(wordArray[0], "").trim();
            eventType = wordArray[0];
        }

        if (taskDescription.isEmpty()) {
            throw new DukeException();
        }

        switch (eventType) {
        case "todo":
            task = new ToDo(taskDescription, isPriority);
            break;
        case "event":
            String[] arrOfWordsEvent = taskDescription.split(" /at ");
            Date parsedEventTime = dateTimeFormat.parse(arrOfWordsEvent[1]);
            task = new Event(arrOfWordsEvent[0], parsedEventTime, isPriority);
            break;
        case "deadline":
            String[] arrOfWordsDeadline = taskDescription.split(" /by ");
            Date parsedDeadline = dateTimeFormat.parse(arrOfWordsDeadline[1]);
            task = new Deadline(arrOfWordsDeadline[0], parsedDeadline, isPriority);
            break;
        }
        return task;
    }
}
