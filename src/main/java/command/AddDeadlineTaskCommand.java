package com.leeyiyuan.command;

import java.time.LocalDateTime;

import com.leeyiyuan.command.CommandExecuteException;
import com.leeyiyuan.command.Command;
import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.DeadlineTask;
import com.leeyiyuan.task.TaskList;

public class AddDeadlineTaskCommand extends Command {

    protected String title;
    
    protected LocalDateTime by;

    public AddDeadlineTaskCommand(String title, LocalDateTime by) {
        this.title = title;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws CommandExecuteException {
        DeadlineTask task = new DeadlineTask();
        task.setTitle(this.title);
        task.setBy(this.by);
        tasks.add(task);
        
        try {
            storage.save(tasks);
        } catch (StorageException e) {
            throw new CommandExecuteException("Unable to save tasks.");
        }
    }

}
