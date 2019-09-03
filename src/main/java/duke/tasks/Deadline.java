package duke.tasks;

import duke.exceptions.DukeException;
import duke.utils.DukeDate;

import java.text.ParseException;

/** Implements the logic behind an Deadline Task. */
public class Deadline extends Task {
    private DukeDate deadline;

    /**
     * Constructor.
     *
     * @param description String describing title/details of Deadline
     * @param deadline    String describing time of Deadline (in the format specified in DukeDate.dateFormatString)
     * @throws DukeException thrown when deadline String is of invalid format.
     */
    public Deadline(String description, String deadline) throws DukeException {
        super(description);
        try {
            this.deadline = new DukeDate(deadline);
        } catch (ParseException e) {
            throw new DukeException("Unable to created Deadline object. Time in invalid format: " + e.getMessage());
        }

    }

    /**
     * String representation of Deadline object.
     *
     * @return String representation of Deadline object
     */
    public String toString() {
        String statusIcon = getStatusIcon();
        return "[D][" + statusIcon + "] " + this.description + " (by: " + this.deadline + ")";
    }

    /**
     * Generates a String representation of the Deadline Task in a format
     * that is compatible for the Storage object to read and write.
     *
     * @return String representation of the Task (compatibility with Storage class)
     */
    public String getStorageFormat() {
        String storageString = "D | " + super.getStorageFormat() + " | " + this.deadline;
        return storageString;
    }
}
