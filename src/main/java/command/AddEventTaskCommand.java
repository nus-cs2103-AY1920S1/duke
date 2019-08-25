package com.leeyiyuan.command;


import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.EventTask;
import com.leeyiyuan.task.TaskList;
import com.leeyiyuan.ui.Ui;
import java.time.LocalDateTime;

public class AddEventTaskCommand extends Command {

    protected String title;

    protected LocalDateTime at;

    public AddEventTaskCommand(String title, LocalDateTime at) {
        this.title = title;
        this.at = at;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws CommandExecuteException, StorageException {
        EventTask task = new EventTask();
        task.setTitle(this.title);
        task.setAt(this.at);
        tasks.add(task);
        storage.save(tasks);
        ui.showLine("Got it. I've added this task:");
        ui.showLine("  " + task.toString());
        ui.showNumTasks(tasks.size());
    }
}
