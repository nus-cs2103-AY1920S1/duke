package duke.task;

import java.text.ParseException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    /**
     * Initiates a deadline task.
     * @param description content of a deadline task
     */
    public Deadline(String description) {
        super(description);
    }

    /**
     * Initiates a Deadline object.
     * @param description content of a deadline task
     * @param isDone true if the task is done and false if not done
     */
    public Deadline(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Expresses a Deadline object in natural language.
     * @return string representation of a deadline task
     * @throws ParseException if description of deadline cannot be parsed
     */
    public String repr() throws ParseException {
        return "[D][" + getStatusIcon() + "] " + super.formatDescription();
    }
}
