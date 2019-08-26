package seedu.duke.model;

public class Todo extends Task{

    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    public Todo(String description, int status) {
        super(description, status);
        this.type = "T";
    }

    @Override
    public String toString() {
        return "[T]" +  super.toString();
    }
}
