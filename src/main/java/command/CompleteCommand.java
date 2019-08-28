package main.java.command;

import main.java.TaskList;
import main.java.Ui;
import main.java.Storage;
import main.java.task.Task;
import main.java.exception.DukeException;

public class CompleteCommand extends Command {
    private int taskId;

    public CompleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.get(this.taskId);
        task.markAsDone();
        storage.save(tasks);
        ui.showTaskCompletionMsg(task);
    }
}