package myduke.task;

import java.util.Scanner;

import myduke.exception.DukeException;
import myduke.exception.DukeEmptyDescriptionException;
import myduke.task.parameters.DukeDateTime;

/**
 * A Task representing an Event.
 */
public class Event extends Task {
    //Constants
    public static final String DATABASE_UNIQUE_IDENTIFIER = "E";

    //Class variables
    protected final DukeDateTime at;

    /**
     * Constructor for DeadLine Task.
     *
     * @param description description of task.
     * @param at          date of task.
     *
     * @throws DukeEmptyDescriptionException if description or date of task is empty.
     */
    public Event(String description, String at) throws DukeEmptyDescriptionException {
        super(description);

        if (description.isEmpty()) {
            throw new DukeEmptyDescriptionException("The description of a event cannot be empty.");
        } else if (at.isEmpty()) {
            throw new DukeEmptyDescriptionException("The duration of a event cannot be empty.");
        }
        this.at = new DukeDateTime(at);
    }

    /**
     * Parses the query as a Event Task.
     *
     * @param in A query from the user.
     *
     * @return A Event task.
     *
     * @throws DukeException representing any checked exceptions
     */
    public static Task parse(Scanner in) throws DukeException {

        String delimiter = "/at";
        in.useDelimiter(delimiter);
        if (!in.hasNext()) {
            throw new DukeEmptyDescriptionException("The description of an event cannot be empty.");
        }
        String description = in.next().trim();

        in.useDelimiter("\\z");
        if (!in.hasNext()) {
            throw new DukeEmptyDescriptionException("The duration of a event cannot be empty.");
        }
        String at = in.next().substring(delimiter.length()).trim();

        return new Event(description, at);
    }

    /**
     *  Gets the data base descriptor character.
     *
     * @return A unique character to identify the task.
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
                at);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)",
                getDataBaseDescriptor(),
                super.toString(),
                at);
    }
}