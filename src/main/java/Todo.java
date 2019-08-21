public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean done) {
        super(description, done);
    }

    @Override
    public Todo finish() {
        return new Todo(description,true);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
