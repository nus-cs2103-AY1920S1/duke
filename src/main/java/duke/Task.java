package duke;

public class Task {
    String description;
    boolean done;
    public Task(String d) {
        description = d;
        done = false;
    }
    public String getDescription() {
        return description;
    }
    public void markDone() {
        done = true;
    }

    @Override
    public String toString() {
        char statusChar = done ? '✓' : '✗';
        return String.format("[%c] %s", statusChar, getDescription());
    }
}
