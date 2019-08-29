package task;

import misc.Parser;

/**
 * Creates a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    public String unformattedDateTime;
    private String formattedDateTime;

    Deadline(String desc, String dateTime) {
        super(desc);
        unformattedDateTime = dateTime;

        Parser parser = new Parser();
        formattedDateTime = parser.convertStringToTime(dateTime, "deadline");
    }

    /**
     * Constructs a Deadline task with its description, deadline and completion status.
     * This constructor is used only when a Deadline task is read from a local save file.
     * @param desc The description of the deadline.
     * @param dateTime The due date of the deadline, consists of a single date and time.
     * @param isDone The completion status of the Deadline task.
     */
    public Deadline(String desc, String dateTime, boolean isDone) {
        super(desc, isDone);
        unformattedDateTime = dateTime;

        Parser parser = new Parser();
        formattedDateTime = parser.convertStringToTime(dateTime, "deadline");
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), description, formattedDateTime);
    }
}
