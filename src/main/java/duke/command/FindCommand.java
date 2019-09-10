package duke.command;

import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Represents the actions to execute when the command 'deadline' is triggered.
 */

public class FindCommand extends Command {

    String toFind;

    /**
     * Returns an FindCommand object from the commandArray, an array of words
     * which make up the initial user input.
     *
     * @param commandArray Array of Strings that form the initial user input
     */

    public FindCommand(String[] commandArray) {
        StringBuilder stringToFindBuilder = new StringBuilder();
        boolean isFirst = true;
        for (int i = 1; i < commandArray.length; i++) {
            if (!isFirst) {
                toFind += commandArray[i];
                isFirst = false;
                break;
            }
            stringToFindBuilder.append(" " + commandArray[i]);
            this.toFind = stringToFindBuilder.toString();
        }
    }

    /**
     * Prints a list of tasks that contains a substring equal to the String inputted.
     *
     * @param tasks   List of Tasks
     * @param storage External storage where the list of tasks is stored
     */

    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert toFind != null : "toFind should contain an actual String";
        TaskList matchingTasks = tasks.search(toFind);
        return "Here are the tasks in your list:\n"
                + matchingTasks;
    }
}
