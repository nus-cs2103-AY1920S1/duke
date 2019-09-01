package command;

import duke.Storage;
import duke.TaskList;

/**
 * Represents a Command which prompts a printing of the TaskList.
 */
public class ListCommand extends Command {

    /**
     * Prompts Ui to print the list of tasks.
     * @param tasks TaskList which stores the list of tasks.
     * @param storage Storage which saves the task into the text file.
     */
    public String execute(TaskList tasks, Storage storage) {
        return tasks.toString();
    }

    public boolean isExit() {
        return false;
    }
}
