package task;

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