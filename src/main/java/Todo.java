public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toDataFormat() {
        return "T | " + super.toDataFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
