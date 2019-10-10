package com.leeyiyuan.command;


import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.task.TaskList;
import com.leeyiyuan.ui.UserOutputInterface;

/** 
 * Represents a Command to exit.
 */
public class ExitCommand extends Command {

    /** 
     * {@inheritDoc} 
     */
    @Override
    public void execute(TaskList tasks, UserOutputInterface uoi, Storage storage) throws AbortException {
        uoi.showLine("Bye. Hope to see you again soon!");
        throw new AbortException("Abort signal detected.");
    }
}
