package taskpackage;

public class ToDo extends Task {

    protected String date;

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}