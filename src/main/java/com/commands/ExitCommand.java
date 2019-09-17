package com.commands;

import com.TaskList;
import com.util.Storage;
import com.util.ui.*;
import gui.GUIUi;
import com.util.stats.DukeStatistics;

public class ExitCommand extends Command {

    public ExitCommand() {
        super("bye");
    }

    public void execute(TaskList taskList, Storage storage, Ui ui) {
        execute(ui);
    }

    public void execute(Ui ui) {
        ui.showGoodbye();
        new DukeStatistics().addLog(this);
        continuesProgram = false;
    }
}
