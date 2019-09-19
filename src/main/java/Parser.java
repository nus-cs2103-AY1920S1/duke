import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Class to parse the input given by the user. */
class Parser {
    // static SimpleDateFormat needed to create tasks
    static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * Parse the string to determine the command type.
     * @param input Command string.
     * @return String representing the type of command.
     */
    public String parseCommand(String input) {
        return input.split(" ", 2)[0].toLowerCase();
    }

    /**
     * Parse a to-do task from a string.
     * @param taskStr String representing the task.
     * @return A To-Do task.
     * @throws DukeException Exception in case to-do is incomplete.
     */
    public ToDo parseTodo(String input) throws DukeException {
        try {
            String description = input.split(" ", 2)[1];
            return new ToDo(description);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter a to-do description.");
        }
    }

    /**
     * Parse a deadline task from a string.
     * @param taskStr String representing the task.
     * @return A Deadline task.
     * @throws DukeException Exception in case deadline is incomplete.
     */
    public Deadline parseDeadline(String input) throws DukeException {
        try {
            String taskStr = input.split(" ", 2)[1];
            String desc = taskStr.split(" /by ")[0];
            Date date = parseDate(taskStr.split(" /by ")[1]);
            return new Deadline(desc, date);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please provide both deadline description and date.");
        }
    }

    /**
     * Parse an event task from a string.
     * @param taskStr String representing the task.
     * @return An Event task.
     * @throws DukeException Exception in case event is incomplete.
     */
    public Event parseEvent(String input) throws DukeException {
        try {
            String taskStr = input.split(" ", 2)[1];
            String desc = taskStr.split(" /at ")[0];
            Date date = parseDate(taskStr.split(" /at ")[1]);
            return new Event(desc, date);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please provide both event description and date.");
        }
    }

    /**
     * Parse an integer from the string.
     * @param Date String representing the date.
     * @return Parsed date.
     * @throws DukeException Exception in case cannot parse date.
     */
    public Date parseDate(String str) throws DukeException {
        try {
            return format.parse(str);
        } catch (ParseException e) {
            throw new DukeException("Cannot parse date. :(");
        }
    }

    /**
     * Parse an integer from the string.
     * @param indexStr String representing the index.
     * @return Integer representing the index.
     * @throws DukeException Exception in case command is incomplete.
     */
    public int parseInteger(String indexStr) throws DukeException {
        if (indexStr.split(" ").length != 2) {
            throw new DukeException("Please enter in the format `command <index>`.");
        }
        return Integer.parseInt(indexStr.split(" ")[1]);
    }
}
