package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * This command lists down all tasks from the list of tasks.
 */
public class SendTasksCommand extends Command {

    /**
     * Ensures that Duke application continues to read in user inputs.
     *
     * @return not terminated.
     */
    public boolean isTerminated() {
        return false;
    }

    /**
     * Executes listing down of all existing tasks.
     *
     * @param tasklist existing list of tasks.
     * @param ui user interface to inform user what tasks are available.
     * @param storage is not used here.
     */
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        StringBuilder temp = new StringBuilder("Here are the tasks in your list: \n");
        for (int i = 0; i < tasklist.size(); i++) {
            Task item = tasklist.get(i);
            temp.append((i + 1) + "." + item.toString() + "\n");
        }
        return temp.toString();
    }

}
