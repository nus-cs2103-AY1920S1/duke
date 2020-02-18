package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidDateTimeException;
import duke.exception.ListFullException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.format.DateTime;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * This command adds a Deadline to the list of tasks.
 */
public class AddDeadlineCommand extends Command {

    private String task;
    private String deadline;

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
     * @param storage is not used here.
     * @throws DukeException if list of tasks is full or if dateTime provided is invalid.
     */
    public String execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        if (tasklist.size() >= 100) {
            throw new ListFullException();
        } else {
            SimpleDateFormat raw = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Date properDeadline;
            try {
                properDeadline = raw.parse(deadline);
            } catch (ParseException e) {
                throw new InvalidDateTimeException();
            }
            DateTime dateTime = new DateTime();
            String formattedDeadline = dateTime.getDeadline().format(properDeadline);
            tasklist.add(new Deadline(task, formattedDeadline));
            Task current = tasklist.get(tasklist.size() - 1);
            return "Got it. I've added this task: \n"
                    + "  " + current.toString() + "\n"
                    + String.format("Now you have %d tasks in the list.", tasklist.size());
        }
    }
}
