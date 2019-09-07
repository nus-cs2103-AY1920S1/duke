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
        if (tasks.containsTitle(this.title)) {
            throw new CommandExecuteException("A task with the same title already exists.");
        }

        TodoTask task = new TodoTask();
        task.setTitle(this.title);
        tasks.add(task);
        storage.save(tasks);
        
        String text = "Got it. I've added this task:\n";
        text += "  " + task.toString() + "\n";
        text += String.format("Now you have %d %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks");
        uoi.showLine(text);
    }
}
