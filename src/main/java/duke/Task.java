package duke;

public class Task {
    String description;
    public Task(String d) {
        description = d;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
