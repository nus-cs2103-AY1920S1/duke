package myduke.task;

import java.util.Scanner;

import myduke.exception.DukeException;
import myduke.exception.DukeEmptyDescriptionException;
import myduke.task.parameters.DukeDateTime;

/**
 * A Task representing a Deadline.
 */
public class Deadline extends Task {
    protected final DukeDateTime byDate;

    public Deadline(String description, String by) throws DukeEmptyDescriptionException {
        super(description);
        if (description.isEmpty()) {
            throw new DukeEmptyDescriptionException("The description of a deadline cannot be empty.");
        } else if (by.isEmpty()) {
            throw new DukeEmptyDescriptionException("The duration of a deadline cannot be empty.");
        }
        this.byDate = new DukeDateTime(by);
    }

    /**
     * Parses the query as a Deadline Task.
     *
     * @param in A query from the user.
     *
     * @return A Deadline task.
     *
     * @throws DukeException representing any checked exceptions.
     */
    public static Task parse(Scanner in) throws DukeException {

        String delimiter = "/by";
        in.useDelimiter(delimiter);
        if (!in.hasNext()) {
            throw new DukeEmptyDescriptionException("The description of a deadline cannot be empty.");
        }
        String description = in.next().trim();

        in.useDelimiter("\\z");
        if (!in.hasNext()) {
            throw new DukeEmptyDescriptionException("The end date of a deadline cannot be empty.");
        }
        String by = in.next().substring(delimiter.length()).trim();

        return new Deadline(description, by);
    }

    @Override
    public char getDataBaseDescriptor() {
        return 'D';
    }

    @Override
    public String getDataBaseFormat() {
        return String.format("%c | %d | %s | %s |\r\n",
                getDataBaseDescriptor(),
                (isDone ? 1 : 0),
                description,
                byDate);
    }

    @Override
    public String toString() {
        return String.format("[%c]%s (by: %s)",
                getDataBaseDescriptor(),
                super.toString(),
                byDate);
    }
}