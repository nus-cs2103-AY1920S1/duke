package command;

import core.Storage;
import exception.DukeIllegalArgumentException;
import exception.DukeIoException;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * AddEventCommand class.
 *
 * <p>Command to add an Event.
 *
 * @author Marcus Ong
 */
public class AddEventCommand extends AddTaskCommand {
    public AddEventCommand(String commandString, Task task) {
        super(CommandType.ADD_EVENT, commandString, task);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIoException {
        tasks.add(this.task);
        ui.showAddTask(task, tasks.size());
        storage.save(tasks);
    }
}
