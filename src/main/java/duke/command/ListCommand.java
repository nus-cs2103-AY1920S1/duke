package duke.command;

import duke.storage.Storage;
import duke.tasks.TaskList;

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
     * @param storage The Storage for saving tasks to file.
     * @return The response string.
     */
    public String execute(TaskList tasks, Storage storage) {

        // Print all existing items in the list
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        tasks.getList().forEach(x -> sb.append(tasks.getList().indexOf(x) + 1).append(". ").append(x).append("\n"));
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
