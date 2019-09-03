package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

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
    public String[] execute(TaskList taskList) throws DukeException {
        return taskList.doTask(this.taskNumber);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
