package com.commands;

import com.TaskList;
import com.util.Storage;
import com.util.Ui;
import com.util.stats.DukeStatistics;

public class StatsCommand extends Command {

    public StatsCommand() {
        super("stats");
    }

    public void execute(TaskList taskList, Storage storage) {
        execute();
    }

    private void execute() {
        // TODO prints all the stats info
        // TODO gets information and numbers from DukeStatistics
        // TODO uses ui to print them
        System.out.println("I'm in execute() of StatsCommand");
    }

}
