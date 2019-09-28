package duke.task;

import java.util.Date;

import duke.task.Task;

public class Deadline extends Task {
    protected Date by;

    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getFileLine() {
        return super.getFileLine() + " | " + this.by;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * This function compares between two Deadline tasks by their description and then deadline.
     * @param other The other Deadline task to compare to.
     * @return int
     */
    public int compareTo(Deadline other) {
        if (this.description.compareTo(other.description) == 0) {
            return this.by.compareTo(other.by);
        } else {
            return this.description.compareTo(other.description);
        }
    }
}
