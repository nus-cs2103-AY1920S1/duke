package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.text.ParseException;

/**
 * This class controls the delete command.
 */

public class DeleteCommand extends Command {

    private int indexToDelete;

    public DeleteCommand(int indexToDelete) {
        this.indexToDelete =  indexToDelete - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTasks().get(indexToDelete);
        tasks.deleteTask(indexToDelete);
        return ui.removeTask(task, tasks.getNumber());
    }
}
