/**
 * A Command to print all reminders.
 */
class PrintRemindersCommand extends Command {

    /**
     * Executes the command to print all reminders.
     *
     * @param tasks The task list.
     * @param storage The storage that handles saving and loading the task list.
     */
    String execute(TaskList tasks, Storage storage) {
        return tasks.getReminders();
    }

}
