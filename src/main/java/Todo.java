public class Todo extends Task {
    public Todo(String s) {
        super(s);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
