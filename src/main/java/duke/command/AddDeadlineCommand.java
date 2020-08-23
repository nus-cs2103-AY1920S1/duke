package duke.command;

;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.calendar.Date;
import duke.calendar.Time;
import duke.exception.DukeException;
import duke.exception.InsufficientDetailsException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidTimeException;
import duke.exception.MissingDescriptionException;
import duke.task.Deadline;
import duke.task.Task;

/**
 * Represents a deadline command.
 * An <code>AddDeadlineCommand</code> object corresponds to a command to add a <code>Deadline</code> object
 * to a <code>TaskList</code>.
 */
public class AddDeadlineCommand extends Command {
    protected String details;

    /**
     * Constructor for <code>AddDeadlineCommand</code>.
     * @param details Details required to create a <code>Deadline</code> object, which includes a task description,
     *                a <code>Date</code> and/or <code>Time</code>.
     */
    public AddDeadlineCommand(String details) {
        super();
        this.details = details;
    }

    /**
     * Creates a new <code>Deadline</code> object and adds it to input <code>TaskList</code>.
     * Calls the method in the <code>Ui</code> object to output a message.
     * Calls the method in the <code>Storage</code> object to write all <code>Task</code> objects in the
     * <code>TaskList</code> object to the hard disk.
     * @param tasks Instance of <code>TaskList</code> which stores <code>Task</code> objects.
     * @param ui Instance of <code>Ui</code> which handles user input and outputs.
     * @param storage Instance of <code>Storage</code> which stores and loads information to and from the hard disk.
     * @throws MissingDescriptionException If description is missing.
     * @throws duke.exception.InsufficientDetailsException If insufficient details are given.
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
        String[] detailsSplit = detailsSplitFromTags[0].trim().split("/by");
        boolean descriptionIsEmpty = detailsSplit.length == 0 || getAction(detailsSplit).length() == 0;
        boolean hasInsufficientDetails = detailsSplit.length < 2 || getDeadline(detailsSplit).length() == 0;
        if (descriptionIsEmpty) {
            throw new MissingDescriptionException("deadline");
        }
        if (hasInsufficientDetails) {
            throw new InsufficientDetailsException("OOPS!!! The description of a deadline"
                    + " requires a task and/or a due date");
        }
        addDeadline(tasks, ui, storage, detailsSplit, tags);
    }

    private void addDeadline(TaskList tasks, Ui ui, Storage storage, String[] detailsSplit, String tags) {
        try {
            Task taskDeadline = createDeadline(detailsSplit);
            tasks.addTask(taskDeadline);
            super.addTags(taskDeadline, tags);
            int numberOfTasks = tasks.getListSize();
            storage.writeToHardDisk(tasks);
            ui.printAddedMessage(taskDeadline, numberOfTasks);
        } catch (DukeException exception) {
            ui.printException(exception);
        }
    }

    private Task createDeadline(String[] detailsSplit) throws DukeException {
        String action = getAction(detailsSplit);
        String deadline = getDeadline(detailsSplit);
        String[] dateAndTimeSplit = deadline.split(" ");
        Date deadlineDate = createDate(dateAndTimeSplit);
        Time deadlineTime = createTime(dateAndTimeSplit);
        return new Deadline(action, deadlineDate, deadlineTime);
    }

    private String getAction(String[] detailsSplit) {
        return detailsSplit[0].trim();
    }

    private String getDeadline(String[] detailsSplit) {
        return detailsSplit[1].trim();
    }

    private String getDate(String[] dateAndTimeSplit) {
        return dateAndTimeSplit[0];
    }

    private String getTime(String[] dateAndTimeSplit) {
        return dateAndTimeSplit[1];
    }

    private Date createDate(String[] dateAndTimeSplit) throws DukeException {
        String date = getDate(dateAndTimeSplit);
        return new Date(date);
    }

    private Time createTime(String[] dateAndTimeSplit) throws InvalidInputException, InvalidTimeException {
        boolean hasNoSpecifiedTime = dateAndTimeSplit.length == 1;
        boolean hasSpecifiedTime = dateAndTimeSplit.length == 2;
        if (hasNoSpecifiedTime) {
            return new Time("");
        } else if (hasSpecifiedTime) {
            return new Time(getTime(dateAndTimeSplit));
        } else {
            throw new InvalidInputException();
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
     * Returns the input details for the <code>AddDeadlineCommand</code>.
     * @return Details for command.
     */
    public String getDetails() {
        return details;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof AddDeadlineCommand) {
            AddDeadlineCommand obj = (AddDeadlineCommand) o;
            return obj.getDetails().equals(details);
        } else {
            return false;
        }
    }
}
