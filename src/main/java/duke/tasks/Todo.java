package duke.tasks;

import duke.Task;

public class Todo extends Task {
    public Todo(String d) {
        super(d);
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", getStatusChar(), getDescription());
    }
}
