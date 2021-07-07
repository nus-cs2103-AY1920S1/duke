package command;

import core.Storage;
import exception.DukeIllegalArgumentException;
import exception.DukeIoException;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * AddDeadlineCommand class.
 *
 * <p>Command to add a Deadline.
 *
 * @author Marcus Ong
 */
public class AddDeadlineCommand extends AddTaskCommand {
    public AddDeadlineCommand(String commandString, Task task) {
        super(CommandType.ADD_DEADLINE, commandString, task);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIoException {
        tasks.add(this.task);
        ui.showAddTask(task, tasks.size());
        storage.save(tasks);
    }
}
