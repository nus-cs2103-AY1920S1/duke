package com.leeyiyuan.command;


import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.Task;
import com.leeyiyuan.task.TaskList;
import com.leeyiyuan.ui.Ui;

public class TaskDoneCommand extends Command {

    protected int index;

    public TaskDoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws CommandExecuteException, StorageException {
        if (tasks.size() >= this.index) {
            if (tasks.get(this.index - 1).getIsDone()) {
                throw new CommandExecuteException("Task is already marked as done.");
            }
            Task task = tasks.get(this.index - 1);
            task.setIsDone(true);
            storage.save(tasks);
            ui.showLine("Nice! I've marked this task as done:");
            ui.showLine(task.toString());
        } else {
            throw new CommandExecuteException("Task does not exist at index.");
        }
    }
}
