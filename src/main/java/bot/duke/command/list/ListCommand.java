package bot.duke.command.list;

import bot.duke.command.Command;
import bot.duke.storage.Storage;
import bot.duke.task.TaskList;
import bot.duke.ui.Ui;

public class ListCommand extends Command {

    /**
     * Lists the Task objects.
     *
     * @param tasks   The current TaskList object
     * @param ui      The current Ui object
     * @param storage The current Storage object
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(tasks.getTasks());
    }

    @Override
    /**
     * Returns whether this is an exiting command.
     * @return Whether this command exits the application
     */
    public boolean isExit() {
        return false;
    }

}
