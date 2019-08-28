import java.util.Objects;

public class Deadline extends Task{
    protected String by;
    protected Date byDate;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public Deadline(String description, Date byDate) {
        super(description);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        String str = Objects.isNull(byDate) ? by : byDate.toString();
        return "[D]["+ this.getStatusIcon() +"] " + super.toString() + " (by: " + str + ")";
    }

    @Override
    public String toSave() {
        String done = isDone ? "1 | " : "0 | ";
        return "T | " + done + description + " | " + by;
    }
}
