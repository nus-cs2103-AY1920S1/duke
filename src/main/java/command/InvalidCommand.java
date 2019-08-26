package command;

import exception.InvalidInstructionException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class InvalidCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.err.println("     " + new InvalidInstructionException());
    }

    public boolean isExit() {
        return false;
    }
}
