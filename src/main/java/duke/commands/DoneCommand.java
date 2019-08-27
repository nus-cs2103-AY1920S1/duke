package duke.commands;

import duke.ui.UI;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import java.io.IOException;

/**
 * Done command
 */
public class DoneCommand extends Command {
    int i;

    /**
     * Initialises with index of task to be set done.
     * @param i
     */
    public DoneCommand(int i) {
        this.i = i;
    }

    /**
     * Updates task in index as done and updates Storage.
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            String taskMessage = tasks.done(i);
            ui.showDoneMessage(taskMessage);
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.showMessage(e.getMessage());
        }
    }

    /**
     * Does not exit
     * @return false
     */
    public boolean isExit() {
        return false;
    }

}