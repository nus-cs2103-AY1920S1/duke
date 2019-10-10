package com.leeyiyuan.command;


import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.task.Task;
import com.leeyiyuan.task.TaskList;
import com.leeyiyuan.ui.UserOutputInterface;

/** 
 * Represents a Command to list existing tasks. 
 */
public class ListTasksCommand extends Command {

    /** 
     * {@inheritDoc} 
     */
    @Override
    public void execute(TaskList tasks, UserOutputInterface uoi, Storage storage) {
        String text = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            text += String.format("\n%d.%s", i + 1, task.toString());
        }
        uoi.showLine(text);
    }
}
