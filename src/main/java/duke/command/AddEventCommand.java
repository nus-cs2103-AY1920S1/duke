package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.calendar.Date;
import duke.calendar.Time;
import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;

/**
 * Represents an event command.
 * An <code>AddEventCommand</code> object corresponds to a command to add a <code>Event</code> object
 * to a <code>TaskList</code>.
 */
public class AddEventCommand extends Command {
    protected String details;

    /**
     * Constructor for <code>AddEventCommand</code>.
     * @param details Details required to create an <code>Event</code> object, which includes a task description,
     *                a start <code>Date</code> and/or a start <code>Time</code>, an optional end <code>Date</code>
     *                and/or end <code>Time</code>.
     */
    public AddEventCommand(String details) {
        super();
        this.details = details;
    }

    /**
     * Creates a new <code>Event</code> object and adds it into input <code>TaskList</code>.
     * Calls the method in the <code>Ui</code> object to output a message.
     * Calls the method in the <code>Storage</code> object to write all <code>Task</code> objects
     * in the <code>TaskList</code> to the hard disk.
     * @param tasks Instance of <code>TaskList</code> which stores <code>Task</code> objects.
     * @param ui Instance of <code>Ui</code> which handles user input and outputs.
     * @param storage Instance of <code>Storage</code> which stores and loads information to and from the hard disk.
     * @throws DukeException If insufficient or incorrect details are provided.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] detailsSplit = details.split( "/at");
        if (detailsSplit.length == 0 || detailsSplit[0].trim().length() == 0) {
            throw new DukeException("\u2639 OOPS!!! The description of an event cannot be empty.");
        }
        if (detailsSplit.length < 2 || detailsSplit[1].trim().length() == 0) {
            throw new DukeException("\u2639 OOPS!!! The description of an event requires a task and/or a scheduled time");
        }
        String event = detailsSplit[0].trim();
        String timings = detailsSplit[1].trim();
        try {
            String[] startAndEndSplit = timings.split("/to");
            if (startAndEndSplit[0].trim().length() == 0) {
                throw new DukeException("\u2639 OOPS!!! Please input a start time.");
            }
            String startDetails = startAndEndSplit[0].trim();
            String[] startDateAndTimeSplit = startDetails.split(" ");
            String startDate = startDateAndTimeSplit[0];
            String startTime = "";
            if (startDateAndTimeSplit.length == 2) {
                startTime = startDateAndTimeSplit[1];
            } else if (startDateAndTimeSplit.length > 2) {
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            String endDetails = "";
            String endDate = "";
            String endTime = "";
            if (startAndEndSplit.length == 2) {
                endDetails = startAndEndSplit[1].trim();
                String[] endDateAndTimeSplit = endDetails.split(" ");
                endDate = endDateAndTimeSplit[0];
                if (endDateAndTimeSplit.length == 2) {
                    endTime = endDateAndTimeSplit[1];
                } else if (endDateAndTimeSplit.length > 2){
                    throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            Date eventStartDate = new Date(startDate);
            Time eventStartTime = new Time(startTime);
            Date eventEndDate = new Date(endDate);
            Time eventEndTime = new Time(endTime);
            Task taskEvent = new Event(event, eventStartDate, eventStartTime, eventEndDate, eventEndTime);
            tasks.addTask(taskEvent);
            int numberOfTasks = tasks.getListSize();
            ui.printAddedMessage(taskEvent, numberOfTasks);
            storage.writeToHardDisk(tasks);
        } catch (DukeException exception) {
            ui.printException(exception);
        }
    }

    /**
     * Checks if this object is an <code>ExitCommand</code>.
     * @return Whether this command is an exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the input details for the <code>AddEventCommand</code>.
     * @return Details for command.
     */
    public String getDetails() {
        return details;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof AddEventCommand) {
            AddEventCommand obj = (AddEventCommand) o;
            return obj.getDetails().equals(details);
        } else {
            return false;
        }
    }
}
