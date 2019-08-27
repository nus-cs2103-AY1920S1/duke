public class Todo extends Task {

    protected String by;

    public Todo(String description) {
        super(description);
        this.type = "todo";
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "]" + description;
    }
}