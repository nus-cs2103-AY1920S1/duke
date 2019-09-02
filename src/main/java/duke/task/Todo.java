package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String formatToWrite() {
        if (this.done) {
            return String.format("T | %d | %s", 1, this.description);
        } else {
            return String.format("T | %d | %s", 0, this.description);
        }
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
