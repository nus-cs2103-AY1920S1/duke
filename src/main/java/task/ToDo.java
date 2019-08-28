package task;

/**
 * Represents a Task with type Todo
 */
public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean isComplete) {
        super(name, isComplete);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String publishTask() {
        return "T | " + super.publishTask();
    }
}