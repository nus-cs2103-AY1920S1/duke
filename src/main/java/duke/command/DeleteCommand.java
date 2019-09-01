package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Ui;

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
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.deleteTask(this.taskNumber);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
