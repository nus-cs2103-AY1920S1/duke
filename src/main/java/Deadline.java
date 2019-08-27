package DukePkg;

public class Deadline extends DukePkg.Task{
    protected String by;
    public static final String type = "D";

    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
