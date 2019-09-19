package duke.command;

import duke.task.*;
import duke.ui.*;
import duke.storage.*;
import duke.exception.*;

/**
 * This command deletes task from the list of tasks.
 */
public class DeleteTaskCommand extends Command {

    String itemIndex;

    /**
     * Initialises deleting of a task using the task index.
     *
     * @param itemIndex task index.
     */
    public DeleteTaskCommand(String itemIndex) {
        this.itemIndex = itemIndex;
    }

    /**
     * Ensures that Duke application continues to read in user inputs.
     *
     * @return not terminated.
     */
    public boolean isTerminated() {
        return false;
    }

    /**
     * Executes the deletion of a task from the list of tasks.
     * Firstly converts the task index string to an integer.
     * Then check that the task index entered by the user is valid.
     * Finally remove the task from the list of tasks and output what has been successfully deleted using the UI.
     *
     * @param tasklist existing list of tasks.
     * @param ui user interface to inform user what has been deleted.
     * @param storage
     * @throws DukeException if task index is invalid.
     */
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        // convert string to int
        int index = Integer.parseInt(itemIndex) - 1;
        if (index < 0 || index >= tasklist.size()) {
            throw new InvalidIndexException();
        } else {
            Task item = tasklist.get(index);
            // delete task
            tasklist.remove(index);
            ui.sendMessage("Noted. I've removed this task: ");
            ui.sendMessage("  " + item.toString());
            ui.sendMessage(String.format("Now you have %d tasks in the list.", tasklist.size()));
        }
    }

}
