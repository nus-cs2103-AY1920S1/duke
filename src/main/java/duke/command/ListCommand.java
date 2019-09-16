package duke.command;

import duke.model.Task;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.List;

public class ListCommand implements Command {
    @Override
    public void execute(List<Task> tasks, Ui ui, Storage storage) {
        ui.displayTasks("Here are the tasks in your list:", tasks);
    }
}
