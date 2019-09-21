package bot.duke.command.flow;

import java.io.IOException;

import bot.duke.command.Command;
import bot.duke.storage.Storage;
import bot.duke.task.TaskList;
import bot.duke.ui.Ui;

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
     * Returns whether this is an exiting command.
     * @return Whether this command exits the application
     */
    public boolean isExit() {
        return true;
    }

}
