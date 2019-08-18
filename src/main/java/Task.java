public class Task {
    public final String description;
    public final boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task getTaskMarkedAsDone() {
        return new Task(description, true);
    }

    public Task getTaskMarkedUndone() {
        return new Task(description, false);
    }

    private char getStatusIcon() {
        return (char) (isDone ? 0x2713 : 0x2718);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        sb.append(this.getStatusIcon());
        sb.append("] ");
        sb.append(this.description);
        return sb.toString();
    }
}