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
        return input.split(" ", 2)[0];
    }

    /**
     * Parse a to-do task from a string.
     * @param taskStr String representing the task.
     * @return A To-Do task.
     * @throws DukeException Exception in case to-do is incomplete.
     */
    public ToDo parseTodo(String taskStr) throws DukeException {
        if (taskStr.isEmpty()) {
            throw new DukeException("Please enter the to-do description.");
        }
        return new ToDo(taskStr);
    }

    /**
     * Parse a deadline task from a string.
     * @param taskStr String representing the task.
     * @return A Deadline task.
     * @throws DukeException Exception in case deadline is incomplete.
     */
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

    /**
     * Parse an event task from a string.
     * @param taskStr String representing the task.
     * @return An Event task.
     * @throws DukeException Exception in case event is incomplete.
     */
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

    /**
     * Parse an integer from the string.
     * @param indexStr String representing the index.
     * @return Integer representing the index.
     * @throws DukeException Exception in case command is incomplete.
     */
    public int parseInteger(String indexStr) throws DukeException {
        if (indexStr.isEmpty()) {
            throw new DukeException("Please provide an index.");
        }
        return Integer.parseInt(indexStr);
    }
}
