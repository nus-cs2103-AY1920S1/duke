package duke.commands;

import duke.tasks.TaskList;
import duke.Storage;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.getSize() == 0) {
            ui.showNoTask();
        } else {
            ui.showTasks(taskList);
        }
    }
}
