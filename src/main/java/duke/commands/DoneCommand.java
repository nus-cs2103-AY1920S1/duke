package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

import java.io.IOException;

/**
 * Done command.
 */
public class DoneCommand extends Command {
    int index;

    /**
     * Initialises with index of task to be set done.
     *
     * @param i index
     */
    public DoneCommand(int i) {
        this.index = i;
    }

    /**
     * Updates task at index as done and updates Storage.
     *
     * @param tasks   tasks
     * @param ui      ui
     * @param storage storage
     * @return Done message or error message
     */
    public String execute(TaskList tasks, UI ui, Storage storage) {
        try {
            String taskMessage = tasks.done(index);
            storage.save(tasks.getTasks());
            return ui.getDoneMessage(taskMessage);
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