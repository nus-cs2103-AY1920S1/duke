public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String task = "[T][" + this.getStatusIcon() +  "] " + description;
        return task;
    }
}