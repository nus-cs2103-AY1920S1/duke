package commands;

import exceptions.TaskNotFoundException;
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
        try {
            String res = ui.findMessage(taskList.findTasks(key));
            return res;
        } catch (TaskNotFoundException e) {
            return e.getMessage();
        }
    }
}
