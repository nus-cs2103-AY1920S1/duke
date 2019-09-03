package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * DeleteCommand class.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param taskNumber Index of task for operation of command.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * {@inheritDoc}
     */
    public String[] execute(TaskList taskList) throws DukeException {
        return taskList.deleteTask(this.taskNumber);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
