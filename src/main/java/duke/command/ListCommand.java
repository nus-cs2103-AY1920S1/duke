package duke.command;

import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.ui.Message;

/**
 * ListCommand class which lists out all the tasks in the system.
 */
public class ListCommand extends Command {

    @Override
    public CommandResult execute(TaskList taskList) throws DukeException {
        if (taskList.isEmpty()) {
            return new CommandResult(Message.EMPTY_LIST);
        } else {
            return new CommandResult(Message.showList(taskList));
        }
    }
}
