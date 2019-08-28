/**
 * Represents a Task with type Todo
 */
public class ToDo extends Task {
    ToDo(String name) {
        super(name);
    }

    ToDo(String name, boolean isComplete) {
        super(name, isComplete);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    String publishTask() {
        return "T | " + super.publishTask();
    }
}