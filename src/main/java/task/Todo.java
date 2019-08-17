package task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getTaskInitial() {
        return "T";
    }

    public boolean isValid() {
        return this.description != null;
    }
}
