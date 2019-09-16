package duke.command;

import duke.exception.*;
import duke.task.*;
import duke.ui.*;
import duke.storage.*;
import duke.format.*;

/**
 * This command adds a Deadline to the list of tasks.
 */
public class AddDeadlineCommand extends Command {

    String task;
    String deadline;

    /**
     * Initialises the task and its deadline to be added.
     *
     * @param task name of task.
     * @param deadline deadline to be met.
     */
    public AddDeadlineCommand(String task, String deadline) {
        this.task = task;
        this.deadline = deadline;
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
     * Executes the adding of Deadline to the list of tasks.
     * Firstly checks whether list of tasks is already full (maximum 100 tasks).
     * Then reformats date and time of the deadline provided and creates a new Deadline to be added to list of tasks.
     * Finally outputs what has been added successfully to the list of tasks using the UI.
     *
     * @param tasklist existing list of tasks.
     * @param ui user interface to inform user what has been added.
     * @param storage
     * @throws DukeException if list of tasks is full or if dateTime provided is invalid.
     */
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        if (tasklist.size() >= 100) {
            throw new ListFullException();
        } else {
            DateTime dateTime = new DateTime(deadline);
            tasklist.add(new Deadline(task, dateTime.toReformat()));
            Task thing = tasklist.get(tasklist.size() - 1);
            ui.sendMessage("Got it. I've added this task: ");
            ui.sendMessage("  " + thing.toString());
            ui.sendMessage(String.format("Now you have %d tasks in the list.", tasklist.size()));
        }
    }
}
