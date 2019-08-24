package com.leeyiyuan.command;


import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.TaskList;
import com.leeyiyuan.task.TodoTask;

public class AddTodoTaskCommand extends Command {

    protected String title;

    public AddTodoTaskCommand(String title) {
        this.title = title;
    }

    @Override
    public void execute(TaskList tasks, Storage storage)
            throws CommandExecuteException, StorageException {
        TodoTask task = new TodoTask();
        task.setTitle(this.title);
        tasks.add(task);
        storage.save(tasks);
    }
}
