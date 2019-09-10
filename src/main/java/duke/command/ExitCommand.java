package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Message.MESSAGE_EXIT;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.closeScanner();
        return MESSAGE_EXIT;
    }
}