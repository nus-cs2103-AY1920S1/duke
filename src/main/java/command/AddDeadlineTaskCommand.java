package com.leeyiyuan.command;


import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.DeadlineTask;
import com.leeyiyuan.task.TaskList;
import com.leeyiyuan.ui.UserOutputInterface;
import java.time.LocalDateTime;

/** 
 * Represents a Command to add a DeadlineTask. 
 */
public class AddDeadlineTaskCommand extends Command {

    /** 
     * Title for the new DeadlineTask. 
     */
    protected String title;

    /** 
     * By value for the new DeadlineTask. 
     */
    protected LocalDateTime by;

    /**
     * Constructs an AddDeadlineTaskCommand from a title and a by value.
     *
     * @param title The title for the new DeadlineTask.
     * @param by The by value for the new DeadlineTask.
     */
    public AddDeadlineTaskCommand(String title, LocalDateTime by) {
        this.title = title;
        this.by = by;
    }

    /** 
     * {@inheritDoc} 
     */
    @Override
    public void execute(TaskList tasks, UserOutputInterface uoi, Storage storage)
            throws CommandExecuteException, StorageException {
        DeadlineTask task = new DeadlineTask();
        task.setTitle(this.title);
        task.setBy(this.by);
        tasks.add(task);
        storage.save(tasks);
        uoi.showLine("Got it. I've added this task:");
        uoi.showLine("  " + task.toString());
        uoi.showNumTasks(tasks.size());
    }
}
