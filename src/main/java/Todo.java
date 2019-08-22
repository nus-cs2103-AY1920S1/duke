public class Todo extends Task {
    protected String details;

    public Todo(String description, String details) {
        super(description);
        this.details = details;
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + this.description + " " + this.details;
    }
}