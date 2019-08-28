import java.util.Date;

public class Deadline extends Task {
    private Date by;

    Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    @Override
    String saveFormat() {
        return "D|" + super.saveFormat();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
