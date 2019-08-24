package com.leeyiyuan.command;

import com.leeyiyuan.command.CommandExecuteException;
import com.leeyiyuan.command.Command;
import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.TaskList;

public class DeleteTaskCommand extends Command {

    protected int index;

    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws CommandExecuteException {
        if (tasks.size() >= this.index) {
            tasks.remove(this.index - 1);
            
            try {
                storage.save(tasks);
            } catch (StorageException e) {
                throw new CommandExecuteException("Unable to save tasks.");
            }
        } else {
            throw new CommandExecuteException("Task does not exist at index.");
        }
    }

}
