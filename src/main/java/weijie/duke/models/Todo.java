package weijie.duke.models;

public class Todo extends Task {

    /**
     * Constructs a new todo object with the given description.
     * @param description Description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getDateTime() {
        return "";
    }

    @Override
    public String getTaskIcon() {
        return "[T]";
    }
}
