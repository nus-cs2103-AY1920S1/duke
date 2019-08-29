package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class AddCommand extends Command {

    public AddCommand(String command, Task pendingTask) {
        super(command, pendingTask);

    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        Task temp = getPendingTask();
        list.addNewTask(temp);
        list.increaseTaskCount();

        storage.save(list.printList());

        ui.showAddedTask(temp.getFirstCharTask(),
                temp.getIsDone(), temp.getTaskDescription(), list.getTaskCount());
    }

    public boolean isExit() {
        return false;
    }
}
