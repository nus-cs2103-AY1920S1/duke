package myduke.task;

import java.util.Scanner;

import myduke.exception.DukeException;
import myduke.exception.DukeEmptyDescriptionException;
import myduke.task.parameters.DukeDateTime;

/**
 * A Task representing an Event.
 */
public class DoAfter extends Task {
    //Constants
    public static final String DATABASE_UNIQUE_IDENTIFIER = "A";

    //Class variables
    protected final DukeDateTime after;

    /**
     * Constructor for DeadLine Task.
     *
     * @param description description of task.
     * @param after       start date of task.
     *
     * @throws DukeEmptyDescriptionException if description or start date of task is empty.
     */
    public DoAfter(String description, String after) throws DukeEmptyDescriptionException {
        super(description);
        if (description.isEmpty()) {
            throw new DukeEmptyDescriptionException("The description of task cannot be empty.");
        } else if (after.isEmpty()) {
            throw new DukeEmptyDescriptionException("The start date of task cannot be empty.");
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
            throw new DukeEmptyDescriptionException("The description of task cannot be empty.");
        }
        String description = in.next().trim();

        in.useDelimiter("\\z");
        if (!in.hasNext()) {
            throw new DukeEmptyDescriptionException("The start date of task cannot be empty.");
        }
        String after = in.next().substring(delimiter.length()).trim();

        return new DoAfter(description, after);
    }

    /**
     *  Gets the data base descriptor character.
     *
     * @return A unique identifier to identify the task.
     */
    public static String getDataBaseDescriptor() {
        return DATABASE_UNIQUE_IDENTIFIER;
    }

    @Override
    public String getDataBaseFormat() {
        return String.format("%s | %d | %s | %s |\r\n",
                getDataBaseDescriptor(),
                (isDone ? 1 : 0),
                description,
                after);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (after: %s)",
                getDataBaseDescriptor(),
                super.toString(),
                after);
    }
}