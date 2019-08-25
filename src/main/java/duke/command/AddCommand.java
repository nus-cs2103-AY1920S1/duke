package duke.command;

import duke.logic.Storage;
import duke.logic.TaskList;
import duke.task.Task;
import duke.ui.Ui;

import java.io.IOException;

public class AddCommand extends Command {

    public Task taskToAdd;

    public AddCommand(Task t) {
        super(false);
        this.taskToAdd = t;

    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.add(taskToAdd);
        ui.showAddedTask(taskList);
        storage.writeListToFile(taskList);
    }
}
