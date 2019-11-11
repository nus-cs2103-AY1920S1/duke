package seedu.duke.commands;

import seedu.duke.tasks.TaskManager;

/**
 * Shows a footer that is printed when the chat bot is about to terminate.
 */
public class ExitCommand extends Command {
    /**
     * Stop the execution of <code>Duke</code> chat bot.
     *
     * @param taskManager A task manager that handles operations to the list of tasks.
     * @return Notification that Duke chat bot will be terminated.
     */
    @Override
    public CommandResult execute(TaskManager taskManager) {
        return new CommandResult("Bye. Hope to see you again soon!\n");
    }

    public static boolean isExit(Command c) {
        return (c instanceof ExitCommand);
    }
}
