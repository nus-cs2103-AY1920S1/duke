public abstract class Task {
    String description;
    boolean isDone;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public abstract String toString();
}