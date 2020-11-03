package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class DoneCommand extends Command {

    private int taskIndex;

    public DoneCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.markAsDone(taskIndex);
            storage.uploadTasksToFile(taskList.getTasks());
            return ui.doneMessage(taskList.getTasks().get(taskIndex));

        } catch (IndexOutOfBoundsException e) {
            return "Enter a valid task index u pepega :)";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
