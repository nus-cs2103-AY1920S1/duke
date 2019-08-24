package com.leeyiyuan.command;

import com.leeyiyuan.command.CommandExecuteException;
import com.leeyiyuan.command.Command;
import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.TodoTask;
import com.leeyiyuan.task.TaskList;

public class AddTodoTaskCommand extends Command {

    protected String title;

    public AddTodoTaskCommand(String title) {
        this.title = title;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws CommandExecuteException {
        TodoTask task = new TodoTask();
        task.setTitle(this.title);
        tasks.add(task);
        
        try {
            storage.save(tasks);
        } catch (StorageException e) {
            throw new CommandExecuteException("Unable to save tasks.");
        }
    }

}
