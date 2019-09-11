package tasks;

import oop.Date;

/**
 * Deadlines is a form of Task. It contains a description of the task
 * and additional information such as the date that the task is due.
 */
public class Deadlines extends Task {

    /**
     * The information of the Deadline task.
     */
    private String info;

    /**
     * The Date when the Deadline is due.
     */
    private Date details;

    /**
     * Constructs and initializes the attributes of a new Deadlines object.
     * @param description Description of the task.
     * @param info Additional information of the task.
     */
    public Deadlines(String description, String info) {
        super(description);
        this.details = new Date(info);
        this.info = info.trim();
    }

    /**
     * Constructs and initializes the attributes of a new Deadlines object.
     * @param description Description of the task.
     * @param checker Checks the status of the task recorded.
     * @param info Additional information of the task.
     */
    public Deadlines(String description, String checker, String info) {
        super(description, checker);
        this.details = new Date(info);
        this.info = info.trim();
    }

    /**
     * Prints the task in the desired format for storing in text file.
     * @return Returns formatted String representing Deadlines.
     */
    public String getFormattedString() {
        return String.format("D | %s | %s | %s", super.getStatusIcon(), description, info);
    }

    /**
     * Overrides the underlying Object toString method to print Deadlines in
     * the desired format to show to users.
     * @return Returns formatted String representing Deadlines.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + details + ")";
    }
}

