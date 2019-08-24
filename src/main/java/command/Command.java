package com.leeyiyuan.command;


import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.TaskList;

public abstract class Command {

    public abstract void execute(TaskList tasks, Storage storage)
            throws CommandExecuteException, StorageException;
}
