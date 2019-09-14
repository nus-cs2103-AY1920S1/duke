package duke.task;

import duke.io.DateTime;

/**
 * The TaskDeadline class provides an Deadline implementation of the abstract base class Task.
 */
public class TaskDeadline extends Task {
    private TaskDeadline(String description, DateTime by) {
        super(description, by);
        this.type = "D";
    }

    private TaskDeadline(String description, boolean isDone, DateTime by) {
        super(description, isDone, by);
        this.type = "D";
    }

    /**
     * Returns a TaskDeadline object with the given description and time.
     *
     * @param description of the task to be done
     * @param by this time
     * @return a new TaskTodo object.
     */
    public static TaskDeadline of(String description, DateTime by) {
        return new TaskDeadline(description, by);
    }

    /**
     * Returns a TaskDeadline object with the given description, time and truth condition of isDone.
     *
     * @param description of task
     * @param isDone truth condition of the done status of the task
     * @return a new TaskTodo object
     */
    public static TaskDeadline of(String description, boolean isDone, DateTime by) {
        return new TaskDeadline(description, isDone, by);
    }

    /**
     * Returns a TaskDeadline object from its file-formatted-form representation.
     * The expected format is "D | [1|0] | description | by"
     *
     * @param fileFormattedForm of a TaskDeadline object
     * @return a new TaskDeadline object
     */
    public static TaskDeadline ofFileFormattedForm(String fileFormattedForm) {
        String[] elements = fileFormattedForm.split("\\s+\\|\\s+");
        String desc = elements[2];
        DateTime by = DateTime.ofFileFormattedDate(elements[3]);
        boolean done = elements[1].equals(IS_DONE);
        return TaskDeadline.of(desc, done, by);
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
        return String.format("[%s][%s] %s (by: %s)", type, getStatusIcon(), description, time);
    }
}
