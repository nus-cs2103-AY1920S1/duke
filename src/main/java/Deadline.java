import java.util.Date;

public class Deadline extends Task {
<<<<<<< HEAD
    public Deadline(String description, String by) {
=======

    public Deadline(String description, Date by) {
>>>>>>> branch-Level-8
        super(description);
        super.date = by;
        super.type = "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.date + ")";
    }
}
