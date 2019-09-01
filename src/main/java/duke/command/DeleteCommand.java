package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a delete command
 * To remove task from task list, perform Ui task and save updated list to hard disk.
 */
public class DeleteCommand extends Command {
    private int taskNumberToDelete;

    public DeleteCommand(String command, Task pending, int taskNumberToDelete) {
        super(command, pending);
        this.taskNumberToDelete = taskNumberToDelete;
    }

    /**
     * Delete task from task list, perform Ui display and save to hard disk
     *
     * @param list    List containing all tasks.
     * @param ui      Ui interface of duke.
     * @param storage Storage interface.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        Task t = list.deleteTask(taskNumberToDelete - 1);
        storage.save(list.printList());
        ui.showDeletedTask(t, list.getTaskCount());
    }

    /**
     * Return boolean indicating if command is exit command.
     *
     * @return boolean flag indicating if is exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
