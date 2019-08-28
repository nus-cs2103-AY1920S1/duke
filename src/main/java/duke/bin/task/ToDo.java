package duke.bin.task;

public class ToDo extends Task {
    public ToDo (String task) {
        super(task);
    }

    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
