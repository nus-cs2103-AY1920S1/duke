package com.leeyiyuan.command;

import java.time.LocalDateTime;

import com.leeyiyuan.command.CommandExecuteException;
import com.leeyiyuan.command.Command;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.task.EventTask;
import com.leeyiyuan.task.TaskList;

public class AddEventTaskCommand extends Command {

    protected String title;
    
    protected LocalDateTime at;

    public AddEventTaskCommand(String title, LocalDateTime at) {
        this.title = title;
        this.at = at;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws CommandExecuteException, StorageException {
        EventTask task = new EventTask();
        task.setTitle(this.title);
        task.setAt(this.at);
        tasks.add(task);
        storage.save(tasks);
    }

}
