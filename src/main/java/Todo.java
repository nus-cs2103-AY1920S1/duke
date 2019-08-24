public class Todo extends Task {
    Todo(String desc) {
        super(desc);
    }
    Todo(String desc, boolean done) {
        super(desc, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
