package duke.tasklist;

import duke.DukeException;
import duke.io.Parser;

/**
 * Class representing a deadline task in the task list.
 */
public class Deadline extends Task {
    public String time;

    /**
     * Constructs the deadline task (incomplete by default)
     *
     * @param description The description of the deadline task
     * @param time        The time the deadline task needs to be completed
     */
    public Deadline(String description, String time) {
        super(description);
        try {
            this.time = Parser.parseDateTime(time);
        } catch (DukeException e) {
            this.time = time;
        }
    }

    /**
     * Constructs the deadline task along with the completion status of the class
     *
     * @param isComplete  The completion status of the task
     * @param description The description of the task
     * @param time        The time the task needs to be completed by
     */
    public Deadline(boolean isComplete, String description, String time) {
        super(description);
        try {
            this.time = Parser.parseDateTime(time);
        } catch (DukeException e) {
            this.time = time;
        }
        taskCompletionStatus = isComplete;
    }

    /**
     * Returns the string representation of the class
     *
     * @return The string representation of the class
     */
    @Override
    public String toString() {
        return new StringBuilder("[D]").append(super.toString())
                .append(" (by: ")
                .append(time)
                .append(")")
                .toString();
    }
}
