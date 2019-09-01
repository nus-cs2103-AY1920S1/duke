package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Ui;

/**
 * DoneCommand class.
 */
public class DoneCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a DoneCommand object.
     *
     * @param taskNumber Index of task for operation of command.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * {@inheritDoc}
     */
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.doTask(this.taskNumber);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
