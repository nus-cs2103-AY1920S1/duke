package duke.tasks;

/**
 * Represents a Deadline Task.
 */

public class Deadline extends Task {

    String deadline;

    /**
     * Creates a deadline object.
     * @param taskName name of the deadline
     * @param isDone whether the deadline task has been completed
     * @param deadline due date of task
     */

    public Deadline(String taskName, boolean isDone, String deadline) {
        super(taskName, isDone);
        this.deadline = deadline;
    }

    public void setDeadline(String newDeadline) {
        this.deadline = newDeadline;
    }

    /**
     * Returns a string to represent the deadline in the storage file.
     * @return Deadline String in file form
     */

    @Override
    public String toFile() {
        String mark = isDone ? "1" : "0";
        return "D | " + mark + " |" + taskName + "|" + deadline;
    }

    /**
     * Returns deadline in string form to be printed.
     * @return Deadline String in print form
     */

    @Override
    public String toString() {
        String mark = isDone ? "\u2713" : "\u2718";
        return "[D][" + mark + "]" + taskName
                + "(by:" + deadline + ")";
    }

}