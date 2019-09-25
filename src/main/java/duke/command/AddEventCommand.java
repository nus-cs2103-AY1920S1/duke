package duke.command;

import duke.exception.DukeException;
import duke.exception.ListFullException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Event;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.format.DateTime;

/**
 * This command adds an Event to the list of tasks.
 */
public class AddEventCommand extends Command {

    private String task;
    private String time;

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
     * @param storage is not used here.
     * @throws DukeException if list of tasks is full or if dateTime provided is invalid.
     */
    public String execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        if (tasklist.size() >= 100) {
            throw new ListFullException();
        } else {
            DateTime dateTime = new DateTime(time);
            tasklist.add(new Event(task, dateTime.toReformat()));
            Task thing = tasklist.get(tasklist.size() - 1);
            return "Got it. I've added this task: \n"
                    + "  " + thing.toString() + "\n"
                    + String.format("Now you have %d tasks in the list.", tasklist.size());
        }
    }
}
