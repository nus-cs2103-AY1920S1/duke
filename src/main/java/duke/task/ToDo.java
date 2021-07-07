package duke.task;

/**
 * This is the ToDo class. It is a type of Task.
 */

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
