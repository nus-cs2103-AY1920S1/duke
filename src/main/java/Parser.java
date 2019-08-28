import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Class to parse the input given by the user. */
class Parser {
    // static SimpleDateFormat needed to create tasks
    static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    // determine the command
    public String parseCommand(String input) {
        return input.split(" ", 2)[0];
    }

    // parse the to-do task
    public ToDo parseTodo(String taskStr) throws DukeException {
        if (taskStr.isEmpty()) {
            throw new DukeException("Please enter the to-do description.");
        }
        return new ToDo(taskStr);
    }

    // parse the deadline
    public Deadline parseDeadline(String taskStr) throws DukeException {
        if (taskStr.split(" /by ").length < 1) {
            throw new DukeException("Please enter deadline description and date.");
        } else if (taskStr.split(" /by ").length < 2) {
            throw new DukeException("Please provide a deadline date.");
        }
        String desc = taskStr.split(" /by ")[0];
        try {
            Date date = format.parse(taskStr.split(" /by ")[1]);
            return new Deadline(desc, date);
        } catch (ParseException e) {
            throw new DukeException("Could not parse the deadline. :(");
        }
    }

    // parse the event
    public Event parseEvent(String taskStr) throws DukeException {
        if (taskStr.split(" /at ").length < 1) {
            throw new DukeException("Please enter event description and date.");
        } else if (taskStr.split(" /at ").length < 2) {
            throw new DukeException("Please provide a event date.");
        }
        String desc = taskStr.split(" /at ")[0];
        try {
            Date date = format.parse(taskStr.split(" /at ")[1]);
            return new Event(desc, date);
        } catch (ParseException e) {
            throw new DukeException("Could not parse the event. :(");
        }
    }

    // parse an integer from the input
    public int parseInteger(String indexStr) throws DukeException {
        if (indexStr.isEmpty()) {
            throw new DukeException("Please provide an index.");
        }
        return Integer.parseInt(indexStr);
    }
}
