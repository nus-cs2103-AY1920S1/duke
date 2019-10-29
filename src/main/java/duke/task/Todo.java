package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toEncodedString() {
        String isDone = this.isDone ? "1" : "0";
        return "T | " +  isDone + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
