package command;

import core.Storage;
import core.Ui;
import exception.DukeIoException;
import task.Task;
import task.TaskList;

/**
 * AddTaskCommand class.
 *
 * <p>Command to add Task.
 *
 * @author Marcus Ong
 */
public class AddTaskCommand extends Command {
    private Task task;

    public AddTaskCommand(String commandString, Task task) {
        super(commandString);
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIoException {
        tasks.add(this.task);
        ui.showAddTask(task, tasks.size());
        storage.save(tasks);
    }
}
