package duke.task;

import duke.Datetime;

/**
 * Represents a deadline in the task list.
 */
public class Deadline extends Task {
    private Datetime datetime;

    public Deadline(String text, Datetime datetime) {
        super(text);
        this.datetime = datetime;
    }

    public Deadline(String text, boolean done, Datetime datetime) {
        super(text, done);
        this.datetime = datetime;
    }

    /**
     * Returns the Datetime associated with the Deadline.
     * @return the Datetime associated with the Deadline.
     */
    @Override
    public Datetime getDatetime() {
        return this.datetime;
    }

    /**
     * Returns the string representation of the Deadline type.
     * @return the string representation of the Deadline type.
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Returns the string representation of a Deadline.
     * @return the string representation of a Deadline.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getIcon(), this.getText(), this.datetime);
    }
}
