package task;

import task.DateTime;

/**
 * Represents a Task with type Deadline.
 */
public class Deadline extends Task {
    private DateTime dateTime;

    /**
     * Creates a Deadline object.
     *
     * @param name      The name of the Deadline task
     * @param dateTime  The date and time of the task, in DateTime format
     */
    public Deadline(String name, DateTime dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    /**
     * Creates a Deadline object.
     *
     * @param name      The name of the deadline task
     * @param isComplete The completion status of the deadline task (true/false)
     * @param dateTime  The date and time of the task, in DateTime format
     */
    public Deadline(String name, boolean isComplete, DateTime dateTime) {
        super(name, isComplete);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTime + ")";
    }

    @Override
    public String publishTask() {
        return "D | " + super.publishTask() + " | " + this.dateTime;
    }
}