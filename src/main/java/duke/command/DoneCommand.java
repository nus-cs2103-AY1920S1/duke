package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Message;

/**
 * DoneCommand class which marks tasks as completed.
 */
public class DoneCommand extends Command {

    /** Index of tasks to mark as done. **/
    private int doneIndex;

    /**
     * Initializes a DoneCommand.
     * @param doneIndex Index of task to be marked as done.
     */
    public DoneCommand(int doneIndex) {
        this.doneIndex = doneIndex;
    }

    @Override
    public CommandResult execute(TaskList taskList) throws DukeException {
        Task doneTask = taskList.get(doneIndex);
        taskList.setDone(doneTask);
        return new CommandResult(Message.showDoneSuccess(doneTask));
    }
}
