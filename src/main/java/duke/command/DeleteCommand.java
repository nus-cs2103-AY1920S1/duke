package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class representing user command to delete task at a specific index.
 */
public class DeleteCommand extends Command {

    private int toDelete;

    /**
     * Class constructor to initialize index of Task to delete from the List of Tasks.
     *
     * @param toDelete Index to Delete at.
     */
    public DeleteCommand(int toDelete) {
        this.toDelete = toDelete;
    }

    /**
     * Executes the delete command.
     *
     * @param taskList List of Tasks to be modified by command.
     * @param ui Ui object to be called by the command.
     * @param storage Storage object to be called by the command.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (toDelete >= taskList.size() || toDelete < 0) {
                throw new DukeException("☹ OOPS! duke.task.Task "
                        + (toDelete + 1) + " doesn't exist!");
            } else {
                Task curr = taskList.get(toDelete);
                ui.printDeleted(curr);
                taskList.deleteTask(toDelete);
                ui.printNumTasks();
                storage.setChangedTrue();
            }
        } catch (DukeException de) {
            ui.printDukeError(de.getMessage());
        }
    }

    /**
     * Returns false as this is not an exit command.
     *
     * @return False as not exit command.
     */
    public boolean isExit() {
        return false;
    }
}
