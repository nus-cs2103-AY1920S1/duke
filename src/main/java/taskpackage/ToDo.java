package taskpackage;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String isDone, String description) {
        super(description);
        if (isDone.equals("\u2713")) {
            this.isDone = true;
        }
    }

    protected String toDataBase() {
        return "[T] | " + getStatusIcon() + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}