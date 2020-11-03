package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task deleted = taskList.deleteTask(taskIndex);
            storage.uploadTasksToFile(taskList.getTasks());
            return ui.showDelTaskMsg(deleted.toString(), taskList.getListSize());

        } catch (IndexOutOfBoundsException e) {
            return "Enter a valid task index u pepega :)";
        }
    }
}
