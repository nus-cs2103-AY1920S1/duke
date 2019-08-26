public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getDate() {
        return " (by: " + by + ")";
    }

    @Override
    public boolean getStatus() {
        return isDone;
    }

    public String getBy() {
        return by;
    }
}