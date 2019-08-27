package command;

import core.Storage;
import core.Ui;
import exception.DukeIoException;
import exception.InvalidIndexException;
import task.Task;
import task.TaskList;

/**
 * DoneTaskCommand class.
 *
 * <p>Command to set task as done.
 *
 * @author Marcus Ong
 */
public class DoneTaskCommand extends Command {
    private int taskIndex;

    public DoneTaskCommand(String commandString, int taskIndex) {
        super(commandString);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException, DukeIoException {
        Task task = tasks.done(taskIndex);
        ui.showDoneTask(task);
        storage.save(tasks);
    }
}
