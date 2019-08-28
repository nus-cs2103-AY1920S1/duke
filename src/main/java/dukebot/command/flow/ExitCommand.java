package dukebot.command.flow;

import dukebot.command.Command;
import dukebot.storage.Storage;
import dukebot.task.TaskList;
import dukebot.ui.Ui;

import java.io.IOException;

public class ExitCommand extends Command {

    @Override
    /**
     * Saves and Exits from the Task.
     * @param tasks The current TaskList object
     * @param ui The current Ui object
     * @param storage The current Storage object
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.exposeError(e.getMessage());
        }
        ui.printExitMsg();
    }

    @Override
    /**
     * Returns if this is an exiting command.
     * @return Whether this command exits the application
     */
    public boolean isExit() {
        return true;
    }

}
