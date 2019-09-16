package trackr.command;

import trackr.history.HistoryTracker;
import trackr.storage.Storage;
import trackr.tasklist.TaskList;

public class HelpCommand extends Command {

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
                + "delete <task number> - deletes a task from your list based on the specified task "
                + "number!\n\n"
                + "done <task number> - marks a task on your list as completed, please specify the task "
                + "number!\n\n"
                + "find <task name> - finds and displays all tasks matching the name you have specified\n\n"
                + "exit - exits the app and closes this window";
        return result;
    }
}
