package myduke.task;

import java.util.Scanner;

import myduke.exception.DukeException;
import myduke.exception.DukeEmptyDescriptionException;
import myduke.task.parameters.DukeDateTime;

/**
 * A Task representing a Deadline.
 */
public class Deadline extends Task {
    //Constants
    public static final String DATABASE_UNIQUE_IDENTIFIER = "D";

    //Class variables
    protected final DukeDateTime byDate;

    /**
     * Constructor for DeadLine Task.
     *
     * @param description description of task.
     * @param by          due-date of task.
     *
     * @throws DukeEmptyDescriptionException if description or due date of task is empty.
     */
    public Deadline(String description, String by) throws DukeEmptyDescriptionException {
        super(description);
        if (description.isEmpty()) {
            throw new DukeEmptyDescriptionException("The description of a deadline cannot be empty.");
        } else if (by.isEmpty()) {
            throw new DukeEmptyDescriptionException("The due date of a deadline cannot be empty.");
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
            throw new DukeEmptyDescriptionException("The due date of a deadline cannot be empty.");
        }
        String by = in.next().substring(delimiter.length()).trim();

        return new Deadline(description, by);
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
    public boolean equals(Object task) {
        if (task == this) {
            return true;
        } else if (task instanceof Deadline) {
            return this.toString().compareToIgnoreCase(task.toString()) == 0;
        }

        return false;
    }

    @Override
    public String getDataBaseFormat() {
        return String.format("%s | %d | %s | %s |\r\n",
                getDataBaseDescriptor(),
                (isDone ? 1 : 0),
                description,
                byDate);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)",
                getDataBaseDescriptor(),
                super.toString(),
                byDate);
    }
}