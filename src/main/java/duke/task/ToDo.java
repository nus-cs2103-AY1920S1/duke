package duke.task;

/**
 * ToDo Class Object.
 */
public class ToDo extends Task {

    final String TYPE_TODO = "T";

    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String getType() {
        return TYPE_TODO;
    }

}
