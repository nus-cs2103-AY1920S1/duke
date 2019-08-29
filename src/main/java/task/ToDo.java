package task;

/**
 * Represents a Task with type ToDo
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task
     * @param name The name of the ToDo task
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Constructs a new ToDo task
     * @param name The name of the ToDo task
     * @param isComplete The completion status of the task (true/false)
     */
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