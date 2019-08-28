package commands;

import components.Storage;
import components.Ui;
import tasks.TaskList;

public class DisplayTasksCommand implements Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        taskList.displayList();
    }
}
