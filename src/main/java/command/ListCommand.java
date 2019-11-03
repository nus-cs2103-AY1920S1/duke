package command;

import main.Archive;
import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * A ListCommand Object to deal with user requests to enumerate the current task list.
 */
public class ListCommand extends Command {

    /**
     * Creates a new ListCommand object to help list out all all current tasks.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the command to list out all current tasks.
     * 
     * @param tasks     The existing task list
     * @param ui        The Ui object which interacts with the current user
     * @param storage   The Storage object which reads and writes to a specified file
     * @param archive   The Archive object for archiving purposes
     * @return          The message to be displayed upon successful execution
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, Archive archive) {
        String[] arr = new String[tasks.size() + 1];
        arr[0] = "Here are the tasks in your list:";
        for (int i = 1; i <= tasks.size(); i++) {
            arr[i] = i + ". " + tasks.getTask(i).toString();
        }
        String res = ui.dukeEchoString(arr);
        return res;
    }
}
