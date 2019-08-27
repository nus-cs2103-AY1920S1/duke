public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    private String getStatusIcon() {
        return isDone ? "1" : "0";
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", this.getStatusIcon(), description);
    }
}
