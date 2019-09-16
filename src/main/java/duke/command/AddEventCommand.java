package duke.command;

import duke.exception.*;
import duke.task.*;
import duke.ui.*;
import duke.storage.*;
import duke.format.*;

/**
 * This command adds an Event to the list of tasks.
 */
public class AddEventCommand extends Command {

    String task;
    String time;

    /**
     * Initialises the task and its time to be added.
     *
     * @param task name of task.
     * @param time time of event.
     */
    public AddEventCommand(String task, String time) {
        this.task = task;
        this.time = time;
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
     * Executes the adding of Event to the list of tasks.
     * Firstly checks whether list of tasks is already full (maximum 100 tasks).
     * Then reformats date and time of the event provided and creates a new Event to be added to list of tasks.
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
            DateTime dateTime = new DateTime(time);
            tasklist.add(new Event(task, dateTime.toReformat()));
            Task thing = tasklist.get(tasklist.size() - 1);
            ui.sendMessage("Got it. I've added this task: ");
            ui.sendMessage("  " + thing.toString());
            ui.sendMessage(String.format("Now you have %d tasks in the list.", tasklist.size()));
        }
    }
}
