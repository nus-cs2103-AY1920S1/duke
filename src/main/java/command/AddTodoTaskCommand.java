package com.leeyiyuan.command;


import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.TaskList;
import com.leeyiyuan.task.TodoTask;
import com.leeyiyuan.ui.UserOutputInterface;

/** 
 * Represents a Command to add a TodoTask. 
 */
public class AddTodoTaskCommand extends Command {

    /** Title for the new TodoTask. */
    protected String title;

    /**
     * Constructs an AddTodoTaskCommand from a title.
     *
     * @param title The title for the new DeadlineTask.
     */
    public AddTodoTaskCommand(String title) {
        this.title = title;
    }

    /** 
     * {@inheritDoc} 
     */
    @Override
    public void execute(TaskList tasks, UserOutputInterface uoi, Storage storage)
            throws CommandExecuteException, StorageException {
        TodoTask task = new TodoTask();
        task.setTitle(this.title);
        tasks.add(task);
        storage.save(tasks);
        uoi.showLine("Got it. I've added this task:");
        uoi.showLine("  " + task.toString());
        uoi.showNumTasks(tasks.size());
    }
}
