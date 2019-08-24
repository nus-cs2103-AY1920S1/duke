package com.leeyiyuan.command;


import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.task.Task;
import com.leeyiyuan.task.TaskList;

public class ListTasksCommand extends Command {

    @Override
    public void execute(TaskList tasks, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(String.format("%d.%s", i + 1, task.toString()));
        }
    }
}
