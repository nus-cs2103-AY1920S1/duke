package duke.command;

import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.ui.Message;

/**
 * ClearCommand class used to clear the entire list of tasks.
 */
public class ClearCommand extends Command {

    @Override
    public CommandResult execute(TaskList taskList) throws DukeException {
        taskList.clear();
        return new CommandResult(Message.CLEAR_SUCCESS);
    }

}
