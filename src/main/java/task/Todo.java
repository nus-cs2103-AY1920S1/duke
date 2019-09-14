package task;

/**
 * The Todo class defines the requirement of a todo.
 * 
 * @author Joel Loong
 */
public class Todo extends Task {

    public Todo(String description, int priority) {
        super(description, priority);
    }

    public Todo(String description, int isDone, int priority) {
        super(description, isDone, priority);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " " + super.priority;
    }
}