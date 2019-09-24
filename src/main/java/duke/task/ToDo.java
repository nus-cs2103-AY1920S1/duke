package duke.task;

/**
 * The ToDo class extends the Task class. It is the most basic type of task.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
