package com.leeyiyuan.command;


import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.TaskList;

public class DeleteTaskCommand extends Command {

    protected int index;

    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Storage storage)
            throws CommandExecuteException, StorageException {
        if (tasks.size() >= this.index) {
            tasks.remove(this.index - 1);
            storage.save(tasks);
        } else {
            throw new CommandExecuteException("Task does not exist at index.");
        }
    }
}
