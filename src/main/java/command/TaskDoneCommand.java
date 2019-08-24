package com.leeyiyuan.command;

import com.leeyiyuan.command.CommandExecuteException;
import com.leeyiyuan.command.Command;
import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.TaskList;

public class TaskDoneCommand extends Command {

    protected int index;

    public TaskDoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws CommandExecuteException, StorageException {
        if (tasks.size() >= this.index) {
            if (tasks.get(this.index - 1).getIsDone()) {
                throw new CommandExecuteException("Task is already marked as done.");
            }
            tasks.get(this.index - 1).setIsDone(true);
            storage.save(tasks);
        } else {
            throw new CommandExecuteException("Task does not exist at index.");
        }
    }

}
