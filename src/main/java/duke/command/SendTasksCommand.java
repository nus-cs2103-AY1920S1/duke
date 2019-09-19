package duke.command;

import duke.task.*;
import duke.ui.*;
import duke.storage.*;

/**
 * This command lists down all tasks from the list of tasks.
 */
public class SendTasksCommand extends Command{

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
     * @param storage
     */
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.sendMessage("Here are the tasks in your list: ");
        for (int i = 0; i < tasklist.size(); i ++) {
            Task item = tasklist.get(i);
            ui.sendMessage((i + 1) + "." + item.toString());
        }
    }

}
