package duke.commands;

import duke.ui.UI;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import java.io.IOException;

/**
 * Delete command
 */
public class DeleteCommand extends Command {
    int i;

    /**
     * Initialises with index of task to be deleted.
     * @param i
     */
    public DeleteCommand(int i) {
        this.i = i;
    }

    /**
     * Deletes task and updates Storage.
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            String taskMessage = tasks.delete(i);
            ui.showDeleteMessage(taskMessage, tasks.getTasksSize());
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