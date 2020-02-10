package czkay.duke.logic.command;

import czkay.duke.model.TaskList;
import czkay.duke.storage.Storage;

/**
 * A Command to print all reminders.
 */
public class PrintRemindersCommand extends Command {

    /**
     * Executes the command to print all reminders.
     *
     * @param tasks The task list.
     * @param storage The storage that handles saving and loading the task list.
     */
    public String execute(TaskList tasks, Storage storage) {
        return tasks.getReminders();
    }

}
