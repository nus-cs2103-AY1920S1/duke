package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class EditCommand extends Command {
    private int taskIndex;
    private String newDescription;

    public EditCommand(int taskIndex, String newDescription) {
        this.taskIndex = taskIndex;
        this.newDescription = newDescription;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.editTaskDescription(taskIndex, newDescription);
            storage.uploadTasksToFile(taskList.getTasks());
            return ui.showEditMessage(taskIndex, newDescription);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
