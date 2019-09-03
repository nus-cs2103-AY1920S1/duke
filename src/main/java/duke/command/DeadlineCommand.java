package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;

/**
 * DeadlineCommand class.
 */
public class DeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructs a DeadlineCommand object.
     *
     * @param description Description of deadline.
     * @param by Due date of deadline.
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    public String[] execute(TaskList taskList) throws DukeException {
        return taskList.addNewTask(new Deadline(this.description, this.by));
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
