package com.leeyiyuan.command;


import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.Task;
import com.leeyiyuan.task.TaskList;
import com.leeyiyuan.ui.UserOutputInterface;

/** 
 * Represents a Command to find a Task.
 */
public class FindTaskCommand extends Command {

    /** Keyword to search Tasks for. */
    protected String keyword;

    /**
     * Constructs a FindTaskCommand from a keyword.
     *
     * @param keyword Keyword to search Tasks for.
     */
    public FindTaskCommand(String keyword) {
        this.keyword = keyword.toUpperCase();
    }

    /** 
     * {@inheritDoc} 
     */
    @Override
    public void execute(TaskList tasks, UserOutputInterface uoi, Storage storage) {
        String text = "Here are the matching tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getTitle().toUpperCase().contains(this.keyword)) {
                text += String.format("\n%d.%s", i + 1, task.toString());
            }
        }
        uoi.showLine(text);
    }
}
