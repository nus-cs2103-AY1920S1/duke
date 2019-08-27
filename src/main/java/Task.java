public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String doneIcon = isDone ? "+" : " ";
        return String.format("[%s] %s", doneIcon, description);
    }
}
