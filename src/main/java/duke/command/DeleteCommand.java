package duke.command;

import duke.storage.Storage;
import duke.logic.TaskList;
import duke.task.Task;
import duke.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int num) {
        super(false);
        this.index = num-1;

    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task t = taskList.getTask(index);
        taskList.delete(index);
        ui.showDeletedTask(taskList,t);
        storage.writeListToFile(taskList);
    }
}
