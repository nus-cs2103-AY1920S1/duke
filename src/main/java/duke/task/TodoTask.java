package duke.task;

/**
 * Represents a task that the user must do.
 */
public class TodoTask extends Task {

    /**
     * Constructs a TodoTask with the given description.
     * isDone field is set to false by default.
     *
     * @param description The description of this TodoTask.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Constructs a TodoTask with the given description.
     *
     * @param description The description of this TodoTask.
     * @param isDone The status of this TodoTask.
     */
    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the type of this DeadlineTask as a single capital letter.
     *
     * @return The letter T.
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Returns the type, status, and description of this TodoTask.
     *
     * @return The type, status, and description formatted as a String.
     */
    @Override
    public String getStatus() {
        return "[T]" + super.getStatus();
    }

}
