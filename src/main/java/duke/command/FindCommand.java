package duke.command;

import duke.Ui;
import duke.exceptions.DukeException;
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
        String toFind = "";
        boolean isFirst = true;
        for (int i = 1; i < commandArray.length; i++) {
            if (!isFirst) {
                toFind += commandArray[i];
                isFirst = false;
                break;
            }
            toFind += " " + commandArray[i];
            this.toFind = toFind;
        }
    }

    /**
     * Prints a list of tasks that contains a substring equal to the String inputted.
     *
     * @param tasks   List of Tasks
     * @param ui      User Interface displaying the tasks in the TaskList
     * @param storage External storage where the list of tasks is stored
     */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList matchingTasks = tasks.search(toFind);
        ui.showTasks(matchingTasks);
    }
}
