package command;

import core.Storage;
import core.Ui;
import exception.DukeIOException;
import exception.InvalidIndexException;
import task.Task;
import task.TaskList;

public class DeleteTaskCommand extends Command {
    private int taskIndex;

    public DeleteTaskCommand(String commandString, int taskIndex) {
        super(commandString);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException, DukeIOException {
        Task task = tasks.delete(taskIndex);
        ui.replyDeleteTask(task, tasks.size());
        storage.save(tasks);
    }
}
