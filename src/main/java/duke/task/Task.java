package duke.task;

import duke.DukeException;
import duke.ui.ErrorMsgWithParams;
import duke.ui.Unicode;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? Unicode.TICK : Unicode.CROSS); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone(boolean done) {
        isDone = done;
    }

    public String getStatusWithDescription() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Validate the input from the user.
     *
     * @param description Description of the task
     * @param taskName Name of the task e.g. event or deadline
     * @return
     */
    public static boolean validateData(String description, String taskName) throws DukeException {
        if (description.length() <= 0) {
            throw new DukeException(ErrorMsgWithParams.EMPTY_DESCRIPT, taskName);
        }
        return true;
    }

    @Override
    public String toString() {
        return getStatusWithDescription();
    }

    /**
     * Gets the task name, used by storage to identify tasks.
     *
     * @return String denoting task name e.g. todo, event, deadline. In low caps;
     */
    public abstract String getTaskName();
}