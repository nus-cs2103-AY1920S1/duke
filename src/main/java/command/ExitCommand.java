package com.leeyiyuan.command;


import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.task.TaskList;
import com.leeyiyuan.ui.Ui;

/** 
 * Represents a Command to exit.
 */
public class ExitCommand extends Command {

    /** 
     * {@inheritDoc} 
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AbortException {
        ui.showLine("Bye. Hope to see you again soon!");
        throw new AbortException("Abort signal detected.");
    }
}
