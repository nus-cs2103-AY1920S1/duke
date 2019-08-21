package DukePkg;

public class Deadline extends DukePkg.Task{
    protected String by;

    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
