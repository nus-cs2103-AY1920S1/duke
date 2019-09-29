package duke.task;

import duke.command.DukeInvalidArgumentException;

/**
 * Abstraction of a task representing a generic todo.
 */
public class TodoTask extends Task {
    /**
     * Constructor of the todo task.
     * Calls the generic task constructor and initializes its task type.
     *
     * @param description The task description string.
     */
    public TodoTask(String description) throws DukeInvalidArgumentException {
        super(description);
        this.taskType = TaskType.TODO;
        TaskUtil.validateTaskDescription(description);
    }

    /**
     * Retrieves task type specific status strings.
     *
     * @return The task type specific status string.
     */
    public String getStatusText() {
        return String.format("[T][%s] %s", getStatusIcon(), this.description);
    }
}
