import java.util.Scanner;

public class Deadline extends Task {
    protected final String byDate;

    public Deadline(String description, String by) {
        super(description);
        this.byDate = by;
    }

    public static Task parse(Scanner in) throws DukeException {

        String delimiter = "/by ";
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
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate + ")";
    }
}