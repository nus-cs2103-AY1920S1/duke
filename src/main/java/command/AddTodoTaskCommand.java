package com.leeyiyuan.command;


import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.TaskList;
import com.leeyiyuan.task.TodoTask;
import com.leeyiyuan.ui.Ui;

public class AddTodoTaskCommand extends Command {

    protected String title;

    public AddTodoTaskCommand(String title) {
        this.title = title;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws CommandExecuteException, StorageException {
        TodoTask task = new TodoTask();
        task.setTitle(this.title);
        tasks.add(task);
        storage.save(tasks);
        ui.showLine("Got it. I've added this task:");
        ui.showLine("  " + task.toString());
        ui.showNumTasks(tasks.size());
    }
}
