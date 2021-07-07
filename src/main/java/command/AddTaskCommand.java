package command;

import core.Storage;
import ui.Ui;
import exception.DukeIoException;
import task.Task;
import task.TaskList;

/**
 * AddTaskCommand class.
 *
 * <p>Abstract Command for adding a Task.
 *
 * @author Marcus Ong
 */
public abstract class AddTaskCommand extends Command {
    protected Task task;

    public AddTaskCommand(CommandType type, String commandString, Task task) {
        super(type, commandString);
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIoException {
        tasks.add(this.task);
        ui.showAddTask(task, tasks.size());
        storage.save(tasks);
    }
}
