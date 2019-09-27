package trackr.command;

import trackr.history.HistoryTracker;
import trackr.storage.Storage;
import trackr.tasklist.TaskList;

public class HelpCommand extends Command {

    /**
     * Displays all available commands that users can issue to the application.
     * @param tasks List of tasks
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     * @param history Tracks input history
     * @return String Instructions on available commands
     */
    @Override
    public String execute(TaskList tasks, Storage storage, HistoryTracker history) {
        String result = "Here is a list of commands you may use:\n\n"
                + "list - display all the tasks you currently have in your list\n\n"
                + "todo <task name> - adds a task to be done to your list, please specify a name for the "
                + "task!\n\n"
                + "deadline <task name> /by <dd/MM/yyyy HHmm> - adds a deadline to your list, please "
                + "specify a name and date for the deadline!\n\n"
                + "event <task name> /at <dd/MM/yyyy HHmm> - adds a deadline to your list, please specify "
                + "a name and date for the event!\n\n"
                + "remove <task number> - deletes a task from your list based on the specified "
                + "task number!\n\n"
                + "complete <task number> - marks a task on your list as completed, please "
                + "specify the task number!\n\n"
                + "update <task number> <new task name> - changes the name of existing task to a "
                + "new name\n\n"
                + "undo - undo previous command that made changes to the list of tasks\n\n"
                + "find <task name> - finds and displays all tasks matching the name you have specified\n\n"
                + "history - displays all your input history for this session \n\n"
                + "exit - exits the app and closes this window";
        return result;
    }
}
