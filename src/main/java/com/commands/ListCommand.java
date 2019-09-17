package com.commands;

import com.TaskList;
import com.util.StaticStrings;
import com.util.Storage;
import com.util.stats.DukeStatistics;

public class ListCommand extends Command {

    public ListCommand() {
        super("list");
    }

    @Override
    public void execute(TaskList taskList, Storage storage){
        if (taskList.getNumTasks() == 0) {
            ui.showMessage(StaticStrings.NO_TASKS);
        } else {
            ui.showListCommandResponse(taskList.getTaskArr());
        }
        new DukeStatistics().addLog(this);
    }

    /**
     * To help with debugging.
     */
    public void print() {
        super.print();
    }
}