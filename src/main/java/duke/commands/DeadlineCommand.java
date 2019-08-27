package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.ui.UI;

import java.io.IOException;

/**
 * Deadline command.
 */
public class DeadlineCommand extends Command {
    Deadline dl;

    /**
     * Initialise with deadline task.
     *
     * @param dl deadline
     */
    public DeadlineCommand(Deadline dl) {
        this.dl = dl;
    }

    /**
     * Adds deadline to Task List and saves it in Storage.
     *
     * @param tasks   tasks
     * @param ui      ui
     * @param storage storage
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            String taskMessage = tasks.addDeadline(dl);
            ui.showAddedMessage(taskMessage, tasks.getTasksSize());
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.showMessage(e.getMessage());
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