package main.java.command;

import main.java.TaskList;
import main.java.Ui;
import main.java.Storage;

public class ExitCommand extends Command {

    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }
}