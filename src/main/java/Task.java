public class Task {
    private String description;
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    private String getStatusIcon() {
        if (this.isDone) {
            return "[✓] ";
        } else {
            return "[✗] ";
        }
    }

    protected void markAsDone() {
        this.isDone = true;
    }

    public String toDataString() {
        return (this.isDone ? 1 : 0) + " | " + this.description;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}
