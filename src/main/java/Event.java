import java.util.Scanner;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public static Task parse(Scanner in) throws DukeException {

        String delimiter = "/at ";
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

    @Override
    public String toString() {
        return "[E]" + super.toString()+ " (at: " + at + ")";
    }
}