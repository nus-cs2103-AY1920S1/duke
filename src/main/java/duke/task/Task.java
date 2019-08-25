package duke.task;

public class Task {
    public String todo;
    public boolean isCompleted;

    public Task(String todo) {
        this.todo = todo;
        this.isCompleted = false;
    }

    public Task(String todo, boolean isCompleted) {
        this.todo = todo;
        this.isCompleted = isCompleted;
    }

    public String toString() {
        if (isCompleted) {
            return "[Y] " + this.todo;
        } else {
            return "[N] " + this.todo;
        }
    }
}
