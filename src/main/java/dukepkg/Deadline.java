package dukepkg;

public class Deadline extends dukepkg.Task{
    private final String by;
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
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
