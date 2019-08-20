public class Deadline extends Task {
    private String by;

    public Deadline(String s, String by) {
        super(s);
        this.by = by;
    }

    @Override
    public String toString() {
        String mark = (isDone ? "✓" : "✗");
        return String.format("[D][%s] %s (by: %s)", mark, taskDescription, by);
    }
}
