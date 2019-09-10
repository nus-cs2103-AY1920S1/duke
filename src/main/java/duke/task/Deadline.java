package duke.task;

import duke.DukeException;

/**
 *
 */
public class Deadline extends DatedTask {

    public static Deadline of(String description, String date) throws DukeException {
        if (description.length() == 0 || date.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description or date/time of a deadline cannot be empty.");
        }

        return new Deadline(description, date);
    }

    private Deadline(String description, String date) {
        super("D", description, date);
    }

    @Override
    public String toString() {
        return super.toString().concat(String.format(" (by: %s)", getDateTime()));
    }
}
