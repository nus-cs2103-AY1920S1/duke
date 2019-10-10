package com.leeyiyuan.command;


import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.EventTask;
import com.leeyiyuan.task.TaskList;
import com.leeyiyuan.ui.UserOutputInterface;
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
    public void execute(TaskList tasks, UserOutputInterface uoi, Storage storage)
            throws CommandExecuteException, StorageException {
        if (tasks.containsTitle(this.title)) {
            throw new CommandExecuteException("A task with the same title already exists.");
        }

        EventTask task = new EventTask();
        task.setTitle(this.title);
        task.setAt(this.at);
        tasks.add(task);
        storage.save(tasks);
        
        String text = "Got it. I've added this task:\n";
        text += "  " + task.toString() + "\n";
        text += String.format("Now you have %d %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks");
        uoi.showLine(text);
    }
}
