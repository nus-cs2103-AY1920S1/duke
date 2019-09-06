package myduke.task;

import java.util.Scanner;

import myduke.exception.DukeException;
import myduke.exception.DukeEmptyDescriptionException;

/**
 * A Task representing a ToDo Task.
 */
public class ToDo extends Task {
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

    @Override
    public char getDataBaseDescriptor() {
        return 'T';
    }

    @Override
    public String getDataBaseFormat() {
        return String.format("%c | %d | %s |\r\n",
                getDataBaseDescriptor(),
                (isDone ? 1 : 0),
                description);
    }

    @Override
    public String toString() {
        return String.format("[%c]%s",
                getDataBaseDescriptor(),
                super.toString());
    }
}