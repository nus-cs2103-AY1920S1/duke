package duke.command;

import duke.Saved;
import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

public class ListCommand extends Command {

    /**
     * Show the user the current list of tasks.
     *
     * @param tasks to access the list of tasks
     * @param ui to give feedback to user
     */
    public String execute(TaskList tasks, Ui ui) {
        return ui.showList(tasks.getTaskArrayList());
    }
}
