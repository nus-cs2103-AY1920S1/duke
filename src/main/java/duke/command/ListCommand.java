package duke.command;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    /**
     * Initialises a ListCommand.
     */
    public ListCommand() {
    }

    /**
     * Executes the List Command and lists all existing tasks in the TaskList.
     *
     * @param tasks   The TaskList containing all existing tasks.
     * @param ui      The Ui for printing purposes.
     * @param storage The Storage for saving tasks to file.
     * @return The response string.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        // Print all existing items in the list
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        tasks.allTasks.forEach(x -> sb.append((tasks.allTasks.indexOf(x) + 1) + ". " + x + "\n"));
        return sb.toString();
    }

    /**
     * Checks if it is a bye command.
     *
     * @return A boolean: true if it is a bye command.
     */
    public boolean isExit() {
        return false;
    }
}
