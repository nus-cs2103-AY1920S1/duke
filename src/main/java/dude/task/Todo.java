package dude.task;

public class Todo extends Task {

    public Todo(String description, int isDone) {
        super(description, isDone);
        this.type = "T";
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
