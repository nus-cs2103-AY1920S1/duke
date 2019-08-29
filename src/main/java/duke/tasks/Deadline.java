package duke.tasks;

import duke.exceptions.DukeException;
import duke.utils.DukeDate;

import java.text.ParseException;

public class Deadline extends Task {
    private DukeDate deadline;

    public Deadline(String description, String deadline) throws DukeException {
        super(description);
        try {
            this.deadline = new DukeDate(deadline);
        }
        catch (ParseException e) {
            throw new DukeException("Unable to created Deadline object. Time in invalid format: " + e.getMessage());
        }

    }

    public String toString() {
        String statusIcon = getStatusIcon();
        return "[D][" + statusIcon + "] " + this.description + " (by: " + this.deadline + ")";
    }

    public String getStorageFormat() {
        String storageString = "D | " + super.getStorageFormat() + " | " + this.deadline;
        return storageString;
    }
}
