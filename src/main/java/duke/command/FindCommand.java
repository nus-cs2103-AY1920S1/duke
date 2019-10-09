package duke.command;

import duke.util.Storage;
import duke.util.TextUi;

import duke.task.TaskList;

/**
 * A FindCommand contains instructions to find all tasks that match a
 * specific pattern.
 */
public class FindCommand extends Command {

    /**
     * Creates a new FindCommand that can search for the given details.
     *
     * @param details String containing details that found tasks should contain.
     */
    public FindCommand(String details) {
        super(details);
    }

    /**
     * Displays all tasks that match the details contained in the current
     * FindCommand. Tasks are evaluated using their default toString() values
     * and are matched against the current command's details using the String
     * method contains(String).
     *
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Hard disk storage.
     * @return String containing Duke's response.
     */
    @Override
    public String execute(TaskList tasks, TextUi ui, Storage storage) {
        String allTasks = tasks.toIndexedString();
        StringBuilder foundTasks = new StringBuilder();
        for (String task : allTasks.split("\n")) {
            if (task.contains(this.details)) {
                foundTasks.append(task.concat("\n"));
            }
        }
        boolean hasNoMatchingTasks = foundTasks.length() == 0;
        if (hasNoMatchingTasks) {
            return "Hmm... there are no tasks containing this description!\n"
                    + "Did you mean to find something else?";
        }
        return foundTasks.toString();
    }
}
