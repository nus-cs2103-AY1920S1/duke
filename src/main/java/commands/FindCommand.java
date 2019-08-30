package commands;

import logic.DukeException;
import logic.Storage;
import logic.TaskList;
import logic.Ui;
import task.Task;

import java.util.List;

public class FindCommand extends Command {
    private String args;

    public FindCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        List<Task> filteredTasks = tasks.findTask(args);
        ui.printList(filteredTasks, true);
    }
}
