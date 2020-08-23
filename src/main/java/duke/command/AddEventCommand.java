package duke.command;

;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.calendar.Date;
import duke.calendar.Time;
import duke.exception.DukeException;
import duke.exception.InsufficientDetailsException;
import duke.exception.InvalidDateException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidTimeException;
import duke.exception.MissingDescriptionException;
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
     * @throws MissingDescriptionException If description is missing.
     * @throws InsufficientDetailsException If insufficient details are given.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws MissingDescriptionException, InsufficientDetailsException {
        String[] detailsSplitFromTags = details.split("#");
        boolean hasSpecifiedTags = detailsSplitFromTags.length != 1;
        String tags = "";
        if (hasSpecifiedTags) {
            tags = detailsSplitFromTags[1].trim();
            boolean hasNoTag = tags.length() == 0;
            if (hasNoTag) {
                throw new MissingDescriptionException("tag");
            }
        }
        String[] detailsSplit = detailsSplitFromTags[0].trim().split("/at");
        boolean descriptionIsEmpty = detailsSplit.length == 0 || getEvent(detailsSplit).length() == 0;
        boolean scheduleTimeIsEmpty = detailsSplit.length < 2 || getTimings(detailsSplit).length() == 0;
        if (descriptionIsEmpty) {
            throw new MissingDescriptionException("event");
        }
        if (scheduleTimeIsEmpty) {
            throw new InsufficientDetailsException("OOPS!!! The description of an event requires a task and/or"
                    + "a scheduled time");
        }
        addEvent(tasks, ui, storage, detailsSplit, tags);
    }

    private void addEvent(TaskList tasks, Ui ui, Storage storage, String[] detailsSplit, String tags) {
        try {
            Task taskEvent = createEvent(detailsSplit);
            tasks.addTask(taskEvent);
            super.addTags(taskEvent, tags);
            int numberOfTasks = tasks.getListSize();
            storage.writeToHardDisk(tasks);
            ui.printAddedMessage(taskEvent, numberOfTasks);
        } catch (DukeException exception) {
            ui.printException(exception);
        }
    }

    private Task createEvent(String[] detailsSplit) throws InsufficientDetailsException, InvalidInputException,
            InvalidTimeException, InvalidDateException {
        String event = getEvent(detailsSplit);
        String timings = getTimings(detailsSplit);
        String startDetails = getStartDetails(timings);
        boolean noStartDetails = startDetails.length() == 0;
        if (noStartDetails) {
            throw new InsufficientDetailsException("OOPS!!! Please input a start date/time.");
        }
        String endDetails = getEndDetails(timings);
        Date eventStartDate = createStartDate(startDetails);
        Time eventStartTime = createStartTime(startDetails);
        Date eventEndDate = createEndDate(endDetails);
        Time eventEndTime = createEndTime(endDetails);
        return new Event(event, eventStartDate, eventStartTime, eventEndDate, eventEndTime);
    }

    private Date createStartDate(String startDetails) throws InvalidDateException {
        String[] startDateAndTimeSplit = startDetails.split(" ");
        String startDate = getDate(startDateAndTimeSplit);
        return new Date(startDate);
    }

    private Time createStartTime(String startDetails) throws InvalidInputException, InvalidTimeException {
        String[] startDateAndTimeSplit = startDetails.split(" ");
        String startTime = "";
        boolean hasStartTime = startDateAndTimeSplit.length == 2;
        boolean hasWrongFormat = startDateAndTimeSplit.length > 2;
        if (hasStartTime) {
            startTime = getTime(startDateAndTimeSplit);
        } else if (hasWrongFormat) {
            throw new InvalidInputException();
        }
        return new Time(startTime);
    }


    private Date createEndDate(String endDetails) throws InvalidDateException {
        String[] endDateAndTimeSplit = endDetails.split(" ");
        String endDate = getDate(endDateAndTimeSplit);
        return new Date(endDate);
    }

    private Time createEndTime(String endDetails) throws InvalidInputException, InvalidTimeException {
        String[] endDateAndTimeSplit = endDetails.split(" ");
        String endTime = "";
        boolean hasEndTime = endDateAndTimeSplit.length == 2;
        boolean hasWrongFormat = endDateAndTimeSplit.length > 2;
        if (hasEndTime) {
            endTime = getTime(endDateAndTimeSplit);
        } else if (hasWrongFormat) {
            throw new InvalidInputException();
        }
        return new Time(endTime);
    }

    private String getEvent(String[] detailsSplit) {
        return detailsSplit[0].trim();
    }

    private String getTimings(String[] detailsSplit) {
        return detailsSplit[1].trim();
    }

    private String getStartDetails(String timings) {
        String[] startAndEndSplit = timings.split("/to");
        return startAndEndSplit[0].trim();
    }

    private String getDate(String[] dateAndTimeSplit) {
        return dateAndTimeSplit[0].trim();
    }

    private String getTime(String[] dateAndTimeSplit) {
        return dateAndTimeSplit[1].trim();
    }

    private String getEndDetails(String timings) {
        String[] startAndEndSplit = timings.split("/to");
        boolean hasEndDetails = startAndEndSplit.length == 2;
        if (hasEndDetails) {
            return startAndEndSplit[1].trim();
        } else {
            return "";
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
