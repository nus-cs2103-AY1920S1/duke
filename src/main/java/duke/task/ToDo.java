package duke.task;

public class ToDo extends Task {

    /**
     * Instantiates a Task object of type Todo.
     * @param description Str to describe deadline
     */
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, String tagName) {
        super(description,tagName);
    }

    public String getDescription() {
        return super.getDescription();
    }

    public String getTaskTypeLetter() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
