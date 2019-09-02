package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class FindCommand extends Command {
    private String key;

    public FindCommand(String key) {
        this.key = key;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTaskList(taskList.findTasks(key));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
