package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

import java.io.IOException;

/**
 * Delete command.
 */
public class DeleteCommand extends Command {
    int index;

    /**
     * Initialises with index of task to be deleted.
     *
     * @param index index
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes task at index and updates Storage.
     *
     * @param tasks   tasks
     * @param ui      ui
     * @param storage storage
     */
    public String execute(TaskList tasks, UI ui, Storage storage) {
        try {
            String taskMessage = tasks.delete(index);
            storage.save(tasks.getTasks());
            return ui.getDeleteMessage(taskMessage, tasks.getTasksSize());
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Does not exit.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

}