package command;

import core.Storage;
import core.Ui;
import task.TaskList;

public class ListTasksCommand extends Command {

    public ListTasksCommand(String commandString) {
        super(commandString);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(tasks);
    }
}
