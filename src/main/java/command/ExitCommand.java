package com.leeyiyuan.command;

import com.leeyiyuan.command.AbortException;
import com.leeyiyuan.command.Command;
import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.task.TaskList;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Storage storage) throws AbortException {
        throw new AbortException("Abort signal detected.");
    }

}
