package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * TodoCommand class.
 */
public class TodoCommand extends Command {
    private String description;

    /**
     * Constructs a TodoCommand object.
     *
     * @param description Description of ToDo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    public String[] execute(TaskList taskList) throws DukeException {
        return taskList.addNewTask(new ToDo(this.description));
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
