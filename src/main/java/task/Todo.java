package task;

public class Todo extends Task {

    public Todo(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String toFile() {
        if (isDone) {
            return "T-1-" + name;
        } else {
            return "T-0-" + name;
        }
    }

    public String toString() {
        if (isDone) {
            return "[T][✓] " + name;
        } else {
            return "[T][✗] " + name;
        }
    }
}
