package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Command for shut down the bot.
 */
public class ByeCommand extends Command {

    /**
     * Execute deleting a task in user's tasks list.
     * Output bye message and update to hard disk storage.
     * @param tasks the TaskList.
     * @param ui the User Interface which responsible for every output printing.
     * @param storage user's hard disk storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.println("Bye. Hope to see you again soon!");
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
