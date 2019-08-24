package com.leeyiyuan.command;


import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.DeadlineTask;
import com.leeyiyuan.task.TaskList;
import java.time.LocalDateTime;

public class AddDeadlineTaskCommand extends Command {

    protected String title;

    protected LocalDateTime by;

    public AddDeadlineTaskCommand(String title, LocalDateTime by) {
        this.title = title;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Storage storage)
            throws CommandExecuteException, StorageException {
        DeadlineTask task = new DeadlineTask();
        task.setTitle(this.title);
        task.setBy(this.by);
        tasks.add(task);
        storage.save(tasks);
    }
}
