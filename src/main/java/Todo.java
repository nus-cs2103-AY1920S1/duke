public class Todo extends Task {

    public Todo(String taskName, boolean done) {
        super(taskName, done);
    }

    public String toString() {
        if (done) {
            return "[T][✓]" + taskName;
        } else {
            return "[T][✗]" + taskName;
        }
    }

    public String storageFormat() {
        if (done) {
            return "T/✓/" + taskName;
        } else {
            return "T/✗/" + taskName;
        }
    }
}
