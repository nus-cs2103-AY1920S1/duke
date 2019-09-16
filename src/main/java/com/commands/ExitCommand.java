package com.commands;

import com.TaskList;
import com.util.Storage;
import com.util.stats.DukeStatistics;

public class ExitCommand extends Command {

    public ExitCommand() {
        super("bye");
    }

    public void execute(TaskList taskList, Storage storage) {
        execute();
    }

    public void execute() {
        ui.showGoodbye();
        new DukeStatistics().addLog(this);
        continuesProgram = false;
    }
}
