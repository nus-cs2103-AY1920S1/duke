package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.LinkedList;

public class FindCommand extends Command {

    private String toFind;

    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        LinkedList<Task> tasksWithKeyword = tasks.findTasks(toFind);
        ui.printFoundTasks(tasksWithKeyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
