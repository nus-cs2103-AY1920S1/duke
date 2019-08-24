package tasks;

public class ToDo extends Task {

    protected String by;

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String fileString() {
        return "T" + super.fileString();
    }
}