package tasks;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime taskDeadline;
    protected String taskDeadlineString;


    public Deadline(String description, LocalDateTime taskDeadline, String taskDeadlineString) {
        super(description);
        this.taskDeadline = taskDeadline;
        this.taskDeadlineString = taskDeadlineString;
    }

    /**
     * Returns the deadline date as a String as per user input.
     * @return dateline date as a string
     */
    public String getTaskDeadlineString() {
        return taskDeadlineString;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + taskDeadlineString + ")";
    }
}