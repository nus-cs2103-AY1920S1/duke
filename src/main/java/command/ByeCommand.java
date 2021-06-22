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
     * @return Executed output as String.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        return "Bye. Hope to see you again soon!";

    }

}
