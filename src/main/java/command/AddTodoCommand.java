package command;

import core.Storage;
import exception.DukeIllegalArgumentException;
import exception.DukeIoException;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * AddTodoCommand class.
 *
 * <p>Command to add a Todo.
 *
 * @author Marcus Ong
 */
public class AddTodoCommand extends AddTaskCommand {
    public AddTodoCommand(String commandString, Task task) {
        super(CommandType.ADD_TODO, commandString, task);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIoException {
        tasks.add(this.task);
        ui.showAddTask(task, tasks.size());
        storage.save(tasks);
    }
}
