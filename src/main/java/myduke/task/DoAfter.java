package myduke.task;

import java.util.Scanner;

import myduke.exception.DukeException;
import myduke.exception.DukeEmptyDescriptionException;
import myduke.task.parameters.DukeDateTime;

/**
 * A Task representing an Event.
 */
public class DoAfter extends Task {
    protected final DukeDateTime after;

    public DoAfter(String description, String after) throws DukeEmptyDescriptionException {
        super(description);
        if (description.isEmpty()) {
            throw new DukeEmptyDescriptionException("The description of a 'do after' task cannot be empty.");
        } else if (after.isEmpty()) {
            throw new DukeEmptyDescriptionException("The duration of a 'do after' task cannot be empty.");
        }
        this.after = new DukeDateTime(after);
    }

    /**
     * Parses the query as a DoAfter Task.
     *
     * @param in A query from the user.
     *
     * @return A Event task.
     *
     * @throws DukeException representing any checked exceptions
     */
    public static Task parse(Scanner in) throws DukeException {

        String delimiter = "/after";
        in.useDelimiter(delimiter);
        if (!in.hasNext()) {
            throw new DukeEmptyDescriptionException("The description of a 'do after' task cannot be empty.");
        }
        String description = in.next().trim();

        in.useDelimiter("\\z");
        if (!in.hasNext()) {
            throw new DukeEmptyDescriptionException("The start date of a 'do after' task cannot be empty.");
        }
        String after = in.next().substring(delimiter.length()).trim();

        return new DoAfter(description, after);
    }

    @Override
    public char getDataBaseDescriptor() {
        return 'A';
    }

    @Override
    public String getDataBaseFormat() {
        return String.format("%c | %d | %s | %s |\r\n",
                getDataBaseDescriptor(),
                (isDone ? 1 : 0),
                description,
                after);
    }

    @Override
    public String toString() {
        return String.format("[%c]%s (after: %s)",
                getDataBaseDescriptor(),
                super.toString(),
                after);
    }
}