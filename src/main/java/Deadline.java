public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public Deadline(String description, String by, String isDone) {
        super(description, isDone);
        this.by = by;
    }
    @Override
    public String getFormatToFile() {
        return String.format("D | %d | %s | %s \n", (isDone ? 1 : 0), description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}