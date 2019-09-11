package duke.command;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class FindCommand extends Command {

    private String keyword;

    /**
     * Initialises a FindCommand.
     *
     * @param keyword The keyword to find in tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Executes the Find Command and lists all existing tasks in the TaskList that contains the keyword.
     *
     * @param tasks   The TaskList containing all existing tasks.
     * @param storage The Storage for saving tasks to file.
     * @return The response string.
     */
    public String execute(TaskList tasks, Storage storage) {

        // Print all existing items in the list
        boolean hasMatching = false;
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Task x : tasks.getList()) {
            if (x.toString().toLowerCase().contains(keyword)) {
                hasMatching = true;
                sb.append(tasks.getList().indexOf(x) + 1).append(". ").append(x).append("\n");
            }
        }
        if (!hasMatching) {
            sb = new StringBuilder("No matching tasks found.");
        }
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
