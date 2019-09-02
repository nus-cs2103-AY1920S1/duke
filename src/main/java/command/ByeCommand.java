package command;

import run.Storage;
import run.TaskList;
import run.Ui;

public class ByeCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
        ui.closeScanner();
    }
    /**
     * Checks if this command is an exit ("bye") command
     * @return true boolean since command is exit ("bye") command
     */
    public boolean isExit() {
        return true;
    }
}