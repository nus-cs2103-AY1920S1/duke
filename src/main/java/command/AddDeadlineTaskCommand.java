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
        if (tasks.containsTitle(this.title)) {
            throw new CommandExecuteException("A task with the same title already exists.");
        }

        DeadlineTask task = new DeadlineTask();
        task.setTitle(this.title);
        task.setBy(this.by);
        tasks.add(task);
        storage.save(tasks);

        String text = "Got it. I've added this task:\n";
        text += "  " + task.toString() + "\n";
        text += String.format("Now you have %d %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks");
        uoi.showLine(text);
    }
}
