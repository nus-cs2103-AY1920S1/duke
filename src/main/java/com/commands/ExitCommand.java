package com.commands;

import com.TaskList;
import com.util.Storage;

public class ExitCommand extends Command {

    public ExitCommand() {
        super("bye");
    }

    public void execute(TaskList taskList, Storage storage) {
        execute();
    }

    public void execute() {
        ui.showGoodbye();
        continuesProgram = false;
    }
}

