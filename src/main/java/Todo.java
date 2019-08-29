public class Todo extends Task {
    protected String by;
    public Todo(String taskName) {
        super(taskName);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}