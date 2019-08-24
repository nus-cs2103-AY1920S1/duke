package com.leeyiyuan.command;


import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.task.TaskList;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Storage storage) throws AbortException {
        throw new AbortException("Abort signal detected.");
    }
}
