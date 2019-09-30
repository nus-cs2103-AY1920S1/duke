package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidEventException;
import duke.exception.ListFullException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Event;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.format.DateTime;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * This command adds an Event to the list of tasks.
 */
public class AddEventCommand extends Command {

    private String task;
    private String start;
    private String end;

    /**
     * Initialises the task and its start and end times to be added.
     *
     * @param task name of task.
     * @param start start time of event.
     * @param end end time of event.
     */
    public AddEventCommand(String task, String start, String end) {
        this.task = task;
        this.start = start;
        this.end = end;
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
     * Finally outputs to the user what has been added successfully to the list of tasks using the UI.
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
            SimpleDateFormat rawStart = new SimpleDateFormat("dd/MM/yyyy HHmm");
            SimpleDateFormat rawEnd = new SimpleDateFormat("HHmm");
            Date properStart;
            Date properEnd;
            try {
                properStart = rawStart.parse(start);
                properEnd = rawEnd.parse(end);
            } catch (ParseException e) {
                throw new InvalidEventException();
            }
            DateTime dateTime = new DateTime();
            String formattedStart = dateTime.getEventStart().format(properStart);
            String formattedEnd = dateTime.getEventEnd().format((properEnd));
            tasklist.add(new Event(task, formattedStart, formattedEnd));
            Task thing = tasklist.get(tasklist.size() - 1);
            return "Got it. I've added this task: \n"
                    + "  " + thing.toString() + "\n"
                    + String.format("Now you have %d tasks in the list.", tasklist.size());
        }
    }
}
