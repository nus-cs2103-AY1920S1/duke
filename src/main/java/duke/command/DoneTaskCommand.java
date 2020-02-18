package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * This command marks task as done from the list of tasks.
 */
public class DoneTaskCommand extends Command {

    private String itemIndex;

    /**
     * Initialises marking a task as done using the task index.
     *
     * @param itemIndex task index.
     */
    public DoneTaskCommand(String itemIndex) {
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
     * Executes the marking of a task as done from the list of tasks.
     * Firstly converts the task index string to an integer.
     * Then check that the task index entered by the user is valid.
     * Finally mark the task as done from the list of tasks and output what has been successfully
     * completed using the UI.
     *
     * @param tasklist existing list of tasks.
     * @param ui user interface to inform user what has been done.
     * @param storage is not used here.
     * @throws DukeException if task index is invalid.
     */
    public String execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        // convert string to int
        int index = Integer.parseInt(itemIndex) - 1;
        if (index < 0 || index >= tasklist.size()) {
            throw new InvalidIndexException();
        } else {
            Task item = tasklist.get(index);
            // tick completed task
            item.setDone();
            return "Nice! I've marked this task as done: \n"
                    + "  " + item.toString();
        }
    }

}
