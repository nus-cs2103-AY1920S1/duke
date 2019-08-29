package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an add command
 * To add task to task list, perform Ui task and save to hard disk.
 */
public class AddCommand extends Command {

    public AddCommand(String command, Task pendingTask) {
        super(command, pendingTask);

    }

    /**
     * Added new task to task list, perform Ui display and save to hard disk
     *
     * @param list  List containing all tasks.
     * @param ui Ui interface of duke.
     * @param storage Storage interface.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage){
        Task temp = getPendingTask();
        list.addNewTask(temp);
        list.increaseTaskCount();

        storage.save(list.printList());

        ui.showAddedTask(temp.getFirstCharTask(),
                temp.getIsDone(), temp.getTaskDescription(), list.getTaskCount());
    }

    /**
     * Return boolean indicating if command is exit command.
     *
     * @return boolean flag indicating if is exit command.
     */
    public boolean isExit() {
        return false;
    }
}
