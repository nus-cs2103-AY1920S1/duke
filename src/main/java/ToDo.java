public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
    }

    public ToDo(Status status, String taskName) {
        super(status, taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + "\n";
    }

    public String toSaveString() {
        return "T|" + (super.completed == Status.INCOMPLETE ? "0" : "1") + "|" + taskName;
    }

}
