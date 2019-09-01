package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a done command
 * To set task from task list as done, perform Ui task and save updated list to hard disk.
 */
public class DoneCommand extends Command {
    private int taskNumberDone;

    public DoneCommand(String command, Task pending, int taskNumberDone) {
        super(command, pending);

        this.taskNumberDone = taskNumberDone;
    }

    /**
     * Set task from task list as done, perform Ui display and save to hard disk
     *
     * @param list    List containing all tasks.
     * @param ui      Ui interface of duke.
     * @param storage Storage interface.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        Task t = list.setTaskDone(taskNumberDone - 1);
        storage.save(list.printList());
        ui.showDoneTask(t);
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
