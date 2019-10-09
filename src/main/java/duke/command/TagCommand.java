package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * TagCommand class.
 */
public class TagCommand extends Command {
    private int taskNumber;
    private String tag;

    /**
     * Constructs a TagCommand object.
     *
     * @param taskNumber Index of task for operation of command.
     */
    public TagCommand(int taskNumber, String tag) {
        this.taskNumber = taskNumber;
        this.tag = tag;
    }

    /**
     * {@inheritDoc}
     */
    public String[] execute(TaskList taskList) throws DukeException {
        return taskList.tagTask(this.taskNumber, this.tag);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
