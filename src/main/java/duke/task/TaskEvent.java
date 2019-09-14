package duke.task;

import duke.io.DateTime;

/**
 * The TaskEvent class provides an Event implementation of the abstract base class Task.
 */
public class TaskEvent extends Task {

    private TaskEvent(String description, DateTime at) {
        super(description, at);
        this.type = "E";
    }

    private TaskEvent(String description, boolean isDone, DateTime at) {
        super(description, isDone, at);
        this.type = "E";
    }

    /**
     * Returns a TaskEvent object with the given description and time.
     *
     * @param description of the task occurring
     * @param at this time
     * @return a new TaskEvent object.
     */
    public static TaskEvent of(String description, DateTime at) {
        return new TaskEvent(description, at);
    }

    /**
     * Returns a TaskEvent object with the given description, time and truth condition of isDone.
     *
     * @param description of the task occurring
     * @param at this time
     * @param isDone truth condition of the done status of the task
     * @return a new TaskEvent object
     */
    public static TaskEvent of(String description, boolean isDone, DateTime at) {
        return new TaskEvent(description, isDone, at);
    }

    /**
     * Returns a TaskEvent object from its file-formatted-form representation.
     * The expected format is "E | [1|0] | description | at"
     *
     * @param fileFormattedForm of a TaskEvent object
     * @return a new TaskEvent object
     */
    public static TaskEvent ofFileFormattedForm(String fileFormattedForm) {
        String[] elements = fileFormattedForm.split("\\s+\\|\\s+");
        String desc = elements[2];
        DateTime at = DateTime.ofFileFormattedDate(elements[3]);
        boolean done = elements[1].equals(IS_DONE);
        return TaskEvent.of(desc, done, at);
    }

    /**
     * Returns the String representation of this Task, for writing to file.
     * 
     * @return the String representation of this Task suitable for writing to file
     */
    public String toFileFormattedString() {
        String status = getStatusIcon().equals(IS_DONE) ? IS_DONE : IS_NOT_DONE;
        return String.format("%s | %s | %s | %s", type, status, description, time);
    }

    /**
     * Returns the String representation of this Task.
     *
     * @return the String representation of this Task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", type, getStatusIcon(), description, time);
    }
}
