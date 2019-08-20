public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return taskID + ".[T]" + super.toStringNoID() + "\n";
    }

    @Override
    public String toStringNoID() {
        return "[T]" + super.toStringNoID() + "\n";
    }
}
