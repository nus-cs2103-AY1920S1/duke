package command;

import core.Storage;
import core.Ui;
import task.TaskList;

/**
 * ListTaskCommand class.
 *
 * <p>Command to show all tasks in list.
 *
 * @author Marcus Ong
 */
public class ListTasksCommand extends Command {

    public ListTasksCommand(String commandString) {
        super(commandString);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(tasks);
    }
}
