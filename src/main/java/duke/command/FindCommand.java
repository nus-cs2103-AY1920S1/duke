package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String toFind;

    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> filteredTasks = tasks.find(toFind);
        ui.printList(filteredTasks, "Here are the matching tasks in your list:");
    }
}
