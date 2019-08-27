package command;

import core.Storage;
import core.Ui;
import exception.DukeIoException;
import exception.InvalidIndexException;
import task.Task;
import task.TaskList;

/**
 * DeleteTaskCommand class.
 *
 * <p>Command to delete Task.
 *
 * @author Marcus Ong
 */
public class DeleteTaskCommand extends Command {
    private int taskIndex;

    public DeleteTaskCommand(String commandString, int taskIndex) {
        super(commandString);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException, DukeIoException {
        Task task = tasks.delete(taskIndex);
        ui.showDeleteTask(task, tasks.size());
        storage.save(tasks);
    }
}
