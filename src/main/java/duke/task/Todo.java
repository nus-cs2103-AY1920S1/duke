package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public StringBuilder saveData() {
        return new StringBuilder("T|").append(super.saveData());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}