package command;

import run.Storage;
import run.TaskList;
import run.Ui;

public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.list();
    }

    /**
     * Checks if this command is an exit ("bye") command
     * @return false boolean since command is not exit ("bye") command
     */
    public boolean isExit() {
        return false;
    }
}