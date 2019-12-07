package com.commands;

import com.TaskList;
import com.util.Storage;
import com.util.ui.*;
import gui.GUIUi;
import com.util.stats.DukeStatistics;
import javafx.application.Platform;

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
        // Close window
        if (ui instanceof GUIUi) {
            Platform.exit();
            System.exit(0);
        }
        continuesProgram = false;
    }
}
