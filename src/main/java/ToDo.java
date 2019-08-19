import java.util.Scanner;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public static Task parse(Scanner in) throws DukeException {
        in.useDelimiter("\\z");
        if (!in.hasNext()) {
            throw new DukeException("The description of a task cannot be empty.");
        }

        return new ToDo(in.next().trim());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}