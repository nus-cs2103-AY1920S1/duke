package com.leeyiyuan.command;


import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.EventTask;
import com.leeyiyuan.task.TaskList;
import com.leeyiyuan.ui.Ui;
import java.time.LocalDateTime;

/** 
 * Represents a Command to add an EventTask. 
 */
public class AddEventTaskCommand extends Command {

    /** Title for new EventTask. */
    protected String title;

    /** At value for the new DeadlineTask. */
    protected LocalDateTime at;

    /**
     * Constructs an AddEventTaskCommand from a title and an at value.
     *
     * @param title The title for the new EventTask.
     * @param at The at value for the new EventTask.
     */
    public AddEventTaskCommand(String title, LocalDateTime at) {
        this.title = title;
        this.at = at;
    }

    /** 
     * {@inheritDoc} 
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws CommandExecuteException, StorageException {
        EventTask task = new EventTask();
        task.setTitle(this.title);
        task.setAt(this.at);
        tasks.add(task);
        storage.save(tasks);
        ui.showLine("Got it. I've added this task:");
        ui.showLine("  " + task.toString());
        ui.showNumTasks(tasks.size());
    }
}
