package com.leeyiyuan.command;


import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.Task;
import com.leeyiyuan.task.TaskList;
import com.leeyiyuan.ui.UserOutputInterface;

/** 
 * Represents a Command to delete an existing Task. 
 */
public class DeleteTaskCommand extends Command {

    /** Index of the Task to delete. */
    protected int index;

    /**
     * Constructs a DeleteTaskCommand from an index.
     *
     * @param index The index of the Task to delete.
     */
    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    /** 
     * {@inheritDoc} 
     */
    @Override
    public void execute(TaskList tasks, UserOutputInterface uoi, Storage storage)
            throws CommandExecuteException, StorageException {
        if (tasks.size() >= this.index) {
            Task task = tasks.remove(this.index - 1);
            storage.save(tasks);
        
            String text = "Noted. I've removed this task:\n";
            text += "  " + task.toString() + "\n";
            text += String.format("Now you have %d %s in the list.", 
                    tasks.size(), tasks.size() == 1 ? "task" : "tasks");
            uoi.showLine(text);
        } else {
            throw new CommandExecuteException("Task does not exist at index.");
        }
    }
}
