package duke.command;

import duke.exception.DukeException;
import duke.task.ToDo;
import duke.task.TaskList;
import duke.util.Ui;

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
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.addNewTask(new ToDo(this.description));
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
