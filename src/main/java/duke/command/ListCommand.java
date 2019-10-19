package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Message.MESSAGE_INDEX;
import static duke.ui.Message.MESSAGE_NO_TASKS_IN_LIST;
import static duke.ui.Message.concatLines;

/**
 * List all the tasks in the task list.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            return MESSAGE_NO_TASKS_IN_LIST;
        } else {
            return concatLines(MESSAGE_INDEX, tasks.toString());
        }
    }
}