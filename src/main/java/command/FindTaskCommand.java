package com.leeyiyuan.command;


import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.Task;
import com.leeyiyuan.task.TaskList;
import com.leeyiyuan.ui.Ui;

public class FindTaskCommand extends Command {

    protected String keyword;

    public FindTaskCommand(String keyword) {
        this.keyword = keyword.toUpperCase();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getTitle().toUpperCase().contains(this.keyword)) {
                ui.showLine(String.format("%d.%s", i + 1, task.toString()));
            }
        }
    }
}
