package tasks;

import oop.Date;

/**
 * The Event class is a type of task. It contains a description of the
 * event and additional information such as the date of the event.
 */
public class Event extends Task {
    /**
     * The information of the Event task.
     */
    private String info;

    /**
     * The Date when the Event occurs.
     */
    private Date details;

    /**
     * Constructs and initializes the attributes of a new Event object.
     * @param description Description of the task.
     * @param info Additional information of the task.
     */
    public Event(String description, String info) {
        super(description);
        this.details = new Date(info);
        this.info = info.trim();
    }

    /**
     * Constructs and initializes the attributes of a new Event object.
     * @param description Description of the task.
     * @param checker Checks the status of the task recorded.
     * @param info Additional information of the task.
     */
    public Event(String description, String checker, String info) {
        super(description, checker);
        this.details = new Date(info);
        this.info = info.trim();
    }

    /**
     * Prints the task in the desired format for storing in text file.
     * @return Returns formatted String representing Event.
     */
    public String getFormattedString() {
        return String.format("E | %s | %s | %s", super.getStatusIcon(), description, info);
    }

    /**
     * Overrides the underlying Object toString method to print Event in
     * the desired format to show to users.
     * @return Returns formatted String representing Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + details + ")";
    }
}

