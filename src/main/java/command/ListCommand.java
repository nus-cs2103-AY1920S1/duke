package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a Command which prompts a printing of the TaskList.
 */
public class ListCommand extends Command {

    /**
     * Prompts Ui to print the list of tasks.
     * @param tasks TaskList which stores the list of tasks.
     * @param ui Ui which feedbacks to user about success of command.
     * @param storage Storage which saves the task into the text file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print(tasks.toString());
    }

    public boolean isExit() {
        return false;
    }
}
