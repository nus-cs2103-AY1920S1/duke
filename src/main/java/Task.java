public class Task {
    protected String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        String doneIcon = done ? "✓" : "✗";
        return String.format("[%s] %s", doneIcon, description);
    }
}
