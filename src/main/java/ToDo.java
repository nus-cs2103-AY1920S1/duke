import java.util.Scanner;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Parses the query as a ToDo Task.
     * @param in A query from the user.
     * @return A ToDo task
     * @throws DukeException representing any checked exceptions
     */
    public static Task parse(Scanner in) throws DukeException {
        in.useDelimiter("\\z");
        if (!in.hasNext()) {
            throw new DukeEmptyDescriptionException("The description of a task cannot be empty.");
        }

        return new ToDo(in.next().trim());
    }

    @Override
    public String getDataBaseFormat() {
        return String.format("T | %d | %s |\r\n", (isDone ? 1 : 0), description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}