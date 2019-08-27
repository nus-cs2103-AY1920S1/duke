package command;

import core.Storage;
import core.Ui;
import exception.DukeIoException;
import task.Task;
import task.TaskList;

/**
 * FindTaskCommand class.
 *
 * <p>Command to find a Task by searching for a keyword.
 *
 * @author Marcus Ong
 */
public class FindTaskCommand extends Command {
    private String searchString;

    public FindTaskCommand(String commandString, String searchString) {
        super(commandString);
        this.searchString = searchString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIoException {
        TaskList matchingTasks = tasks.find(this.searchString);
        ui.showFoundTasks(matchingTasks);
    }
}
