package duke.task;

/**
 * ToDo Class Object.
 */
public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String getType() {
        return "T";
    }

}
