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
     * @return Added Deadline message or error message
     */
    public String execute(TaskList tasks, UI ui, Storage storage) {
        try {
            String taskMessage = tasks.addTask(dl);
            storage.save(tasks.getTasks());
            return ui.getAddedMessage(taskMessage, tasks.getTasksSize());
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