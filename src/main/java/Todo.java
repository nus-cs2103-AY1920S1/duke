public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    public String toString() {
        if (done) {
            return "[T][✓]" + taskName;
        } else {
            return "[T][✗]" + taskName;
        }
    }
}
