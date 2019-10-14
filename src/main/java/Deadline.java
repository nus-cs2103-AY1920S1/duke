package duke.task;

import duke.exception.DukeException;
import duke.exception.MissingPreposition;

/**
 * Represent a deadline object to be created. Extends from the Task class.
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Creates a deadline object. The description should be in the form (description
     * /by time) for the object to be created correctly. The deadline can be given
     * in text or given in dd/mm/yyyy hhmm to be recognized as a DukeDate.
     * 
     * @param description the description of the task
     * @param deadline    the time in which the task needs to be completed
     * @throws MissingPreposition if time does not contain a preposition by
     */
    public Deadline(String description, String deadline) throws DukeException {
        super(description);
        String[] temp = deadline.split(" ", 2);
        try {
            deadline = temp[0] + ": " + temp[1];
            this.deadline = deadline;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingPreposition("deadline");
        }
    }

    /**
     * Converts the deadline object to a text format. Allows the object to be saved
     * more easily in a text file.
     * 
     * @return a text representing the object
     */
    public String toText() {
        return "D|" + super.toText() + "|" + String.join(" ", this.deadline.split(": "));
    }

    /**
     * Express the deadline object in its string representation.
     * 
     * @return a string representing the object
     */
    public String toString() {
        return "[D]" + super.toString() + String.format(" (%s)", this.deadline);
    }
}