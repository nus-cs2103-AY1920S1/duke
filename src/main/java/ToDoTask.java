public class ToDoTask extends Task {
    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.description;
    }
}
