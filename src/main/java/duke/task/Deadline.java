package duke.task;

import duke.exception.DukeUnknownInputException;
import duke.time.DateTime;

public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String deadline) throws DukeUnknownInputException {
        super(description);
        // enforce example format 2/12/2019 1800
        if (deadline.split(" ").length != 2
                || deadline.split(" ")[0].split("/").length != 3
                || Integer.valueOf(deadline.split(" ")[1]) < 0
                || Integer.valueOf(deadline.split(" ")[1]) > 2400) {
            throw new DukeUnknownInputException("Unknown deadline String format passed :(");
        }
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, false);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTime.of(deadline) + ')';
    }
}
