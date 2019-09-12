package seedu.duke.trackables;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String... args) {
        super(args);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getAsString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("T").append(" | ").append(isDone ? "1" : "0").append(" | ").append(this.description);
        return sb.toString();
    }
}
