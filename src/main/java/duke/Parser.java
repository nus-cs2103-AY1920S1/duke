package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {

    private String str;

    public Parser(String str) {
        this.str = str;
    }

    public Task parse() throws DukeException, ParseException {
        Task parsedTask = null;
        String[] wordArray = str.split(" ");
        String taskWithoutType = str.replace(wordArray[0], "").trim();

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy, hh:mm a");

        if (taskWithoutType.isEmpty()) {
            throw new DukeException();
        }

        switch (wordArray[0]) {
        case "todo":
            parsedTask = new ToDo(taskWithoutType);
            break;
        case "event":
            String[] arrOfWordsEvent = taskWithoutType.split(" /at ");
            Date parsedEventTime = format.parse(arrOfWordsEvent[1]);
            parsedTask = new Event(arrOfWordsEvent[0], formatter.format(parsedEventTime));
            break;
        case "deadline":
            String[] arrOfWordsDeadline = taskWithoutType.split(" /by ");
            Date parsedDeadline = format.parse(arrOfWordsDeadline[1]);
            parsedTask = new Deadline(arrOfWordsDeadline[0], formatter.format(parsedDeadline));
            break;
        }
        return parsedTask;
    }
}
