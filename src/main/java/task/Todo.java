package task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskInitial() {
        return "T";
    }

    @Override
    public boolean isValid() {
        return this.description != null;
    }

    @Override
    public String invalidMessage() {
        return  "The description of a todo cannot be empty.";
    }
}
