import java.text.ParseException;

public class Deadline extends Task {

    public Deadline(String description) {
        super(description);
    }

    public String repr() throws ParseException {
        return "[D][" + getStatusIcon() + "] " + super.formatDescription();
    }
}
