package com.leeyiyuan.command;

import com.leeyiyuan.command.CommandExecuteException;
import com.leeyiyuan.task.TaskList;
import com.leeyiyuan.storage.Storage;

public abstract class Command {

    public abstract void execute(TaskList tasks, Storage storage) throws CommandExecuteException;

}
