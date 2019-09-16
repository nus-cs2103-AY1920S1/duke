package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.calendar.Date;
import duke.calendar.Time;
import duke.exception.DukeException;
import duke.exception.InsufficientDetailsException;
import duke.exception.InvalidDateException;
import duke.exception.InvalidTimeException;
import duke.task.Event;

/**
 * Represents a <code>Command</code> that adds a new <code>Event</code> object to the <code>TaskList</code>.
 */
public class AddEventCommand extends Command {
    String details;

    /**
     * Constructor for <Code>AddEventCommand</Code>.
     * @param details The unprocessed details of the <code>Event</code> task.
     */
    public AddEventCommand(String details) {
        super();
        this.details = details;
    }

    /**
     * Creates a  new <code>Event</code> object and adds it to the <code>TaskList</code>.
     * Calls methods in <code>Storage</code> and <code>Ui</code> to write the updated <code>TaskList</code> to hard
     * disk and print message to console respectively.
     * @param tasks Instance of <code>TaskList</code> that stores <code>Task</code> objects.
     * @param ui Instance of <code>Ui</code> that handles user input and output.
     * @param storage Instance of <code>Storage</code> that handles writing and loading of information to hard disk.
     * @throws DukeException If provided details are insufficient or invalid.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] taskDetails = getTaskDetails();

        if (details.length() == 0 || taskDetails[0].trim().length() == 0) {
            throw new InsufficientDetailsException("OOPS!!! The description of an event cannot be empty.");
        }
        if (taskDetails.length < 2 || taskDetails[1].trim().length() == 0) {
            throw new InsufficientDetailsException("OOPS!!! The date/time of an event cannot be empty.");
        }

        String taskDescription = getDescription(taskDetails);
        String[] taskSpecifics = getTaskSpecifics(taskDetails);

        String[] rawStarts = getRawStarts(taskSpecifics);
        Date startDate = getStartDate(rawStarts);
        Time startTime = getStartTime(rawStarts);

        String[] rawEnds = getRawEnds(taskSpecifics);
        Date endDate = getEndDate(rawEnds);
        Time endTime = getEndTime(rawEnds);
        Event event = new Event(taskDescription, startDate, startTime, endDate, endTime);

        tasks.addTask(event);
        updateStorage(tasks, ui, storage);
        ui.printAddTaskMessage(event, tasks.getSize());
    }

    private String[] getTaskDetails() {
        return details.split("/at");
    }

    private String getDescription(String[] taskDetails) {
        return taskDetails[0].trim();
    }

    private String[] getTaskSpecifics(String[] taskDetails) throws InsufficientDetailsException {
        String[] taskSpecifics = taskDetails[1].trim().split("/to");
        if (taskSpecifics[0].trim().length() == 0) {
            throw new InsufficientDetailsException("OOPS!!! The starting date/time of an event cannot be empty.");
        }
        return taskSpecifics;
    }

    private String[] getRawStarts(String[] taskSpecifics) {
        return taskSpecifics[0].trim().split(" ");
    }

    private String getRawStartDate(String[] rawStarts) {
        return rawStarts[0];
    }

    private String getRawStartTime(String[] rawStarts) {
        if (rawStarts.length >= 2 && rawStarts[1].trim().length() != 0) {
            return rawStarts[1];
        }
        return null;
    }

    private String[] getRawEnds(String[] taskSpecifics) {
        if (taskSpecifics.length > 1) {
            return taskSpecifics[1].trim().split(" ");
        }
        return new String[0];
    }

    private String getRawEndDate(String[] rawEnds) {
        if (rawEnds.length > 0) {
            return rawEnds[0];
        }
        return null;
    }

    private String getRawEndTime(String[] rawEnds) {
        if (rawEnds.length >= 2 && rawEnds[1].trim().length() != 0) {
            return rawEnds[1];
        }
        return null;
    }

    private Date getStartDate(String[] rawStarts) throws InvalidDateException {
        String rawStartDate = getRawStartDate(rawStarts);
        return new Date(rawStartDate);
    }

    private Time getStartTime(String[] rawStarts) throws InvalidTimeException {
        String rawStartTime = getRawStartTime(rawStarts);
        return new Time(rawStartTime);
    }

    private Date getEndDate(String[] rawEnds) throws InvalidDateException {
        String rawEndDate = getRawEndDate(rawEnds);
        return new Date(rawEndDate);
    }

    private Time getEndTime(String[] rawEnds) throws InvalidTimeException {
        String rawEndTime = getRawEndTime(rawEnds);
        return new Time(rawEndTime);
    }

    private void updateStorage(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.writeToFile(tasks);
        } catch (DukeException exception) {
            ui.printExceptionMessage(exception);
        }
    }

    /**
     * Checks if the current <code>Command</code> is an <code>ExitCommand</code>.
     * @return False.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns unprocessed details.
     * @return Unprocessed details.
     */
    public String getDetails() {
        return details;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof AddEventCommand) {
            AddEventCommand addEventCommand = (AddEventCommand) o;
            return addEventCommand.getDetails().equals(details);
        } else {
            return false;
        }
    }
}
