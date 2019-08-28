import java.util.Date;

public class Deadline extends Task {
    private Date by;

    public Deadline(String taskName, Date by) {
        super(taskName);
        this.by = by;
    }

    @Override
    public String toString() {
        return  "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}

