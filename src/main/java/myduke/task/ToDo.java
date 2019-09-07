package myduke.task;

import java.util.Scanner;

import myduke.exception.DukeException;
import myduke.exception.DukeEmptyDescriptionException;

/**
 * A Task representing a ToDo Task.
 */
public class ToDo extends Task {
    //Constants
    public static final String DATABASE_UNIQUE_IDENTIFIER = "T";

    /**
     * Constructor for ToDo Task.
     *
     * @param description description of task.
     *
     * @throws DukeEmptyDescriptionException if description of task is empty.
     */
    public ToDo(String description) throws DukeEmptyDescriptionException {
        super(description);
        if (description.isEmpty()) {
            throw new DukeEmptyDescriptionException("The description of a todo cannot be empty.");
        }
    }

    /**
     * Parses the query as a ToDo Task.
     *
     * @param in A query from the user.
     *
     * @return A ToDo task.
     *
     * @throws DukeException representing any checked exceptions
     */
    public static Task parse(Scanner in) throws DukeException {
        in.useDelimiter("\\z");
        if (!in.hasNext()) {
            throw new DukeEmptyDescriptionException("The description of a task cannot be empty.");
        }
        String description = in.next().trim();

        return new ToDo(description);
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
        return String.format("%s | %d | %s |\r\n",
                getDataBaseDescriptor(),
                (isDone ? 1 : 0),
                description);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s",
                getDataBaseDescriptor(),
                super.toString());
    }
}