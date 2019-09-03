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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.findMessage(taskList.findTasks(key));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
