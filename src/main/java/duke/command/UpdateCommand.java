package duke.command;

import duke.core.DukeException;
import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

import duke.task.Task;

public class UpdateCommand extends Command {
    private int taskId;
    private String attribute;
    private String newValue;

    public UpdateCommand(int taskId, String attribute, String newValue) {
        super();
        this.taskId = taskId;
        this.attribute = attribute;
        this.newValue = newValue;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.updateTask(taskId, attribute, newValue);
        Task t = tasks.getTask(taskId);
        ui.updatedTask(t, tasks.getSize());
        storage.save(tasks);
    }

    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.updateTask(taskId, attribute, newValue);
        Task t = tasks.getTask(taskId);
        storage.save(tasks);
        return ui.updatedTaskGui(t, tasks.getSize());
    }
}
