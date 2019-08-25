package com.leeyiyuan.command;


import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.Task;
import com.leeyiyuan.task.TaskList;
import com.leeyiyuan.ui.Ui;

public class DeleteTaskCommand extends Command {

    protected int index;

    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws CommandExecuteException, StorageException {
        if (tasks.size() >= this.index) {
            Task task = tasks.remove(this.index - 1);
            storage.save(tasks);
            ui.showLine("Noted. I've removed this task:");
            ui.showLine("  " + task.toString());
            ui.showNumTasks(tasks.size());
        } else {
            throw new CommandExecuteException("Task does not exist at index.");
        }
    }
}
