package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.calendar.Date;
import duke.calendar.Time;
import duke.exception.DukeException;
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
     * @throws DukeException If insufficient or incorrect details are provided.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] detailsSplit = details.split("/by");
        if (detailsSplit.length == 0 || detailsSplit[0].trim().length() == 0) {
            throw new DukeException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
        }
        if (detailsSplit.length < 2 || detailsSplit[1].trim().length() == 0) {
            throw new DukeException("\u2639 OOPS!!! The description of a deadline requires a task and/or a due date");
        }
        String action = detailsSplit[0].trim();
        String deadline = detailsSplit[1].trim();
        String[] dateAndTimeSplit = deadline.split(" ");
        try {
            String date = dateAndTimeSplit[0];
            Date deadlineDate = new Date(date);
            Time deadlineTime;
            if (dateAndTimeSplit.length == 1) {
                deadlineTime = new Time("");
            } else if (dateAndTimeSplit.length == 2){
                deadlineTime = new Time(dateAndTimeSplit[1]);
            } else {
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            Task taskDeadline = new Deadline(action, deadlineDate, deadlineTime);
            tasks.addTask(taskDeadline);
            int numberOfTasks = tasks.getListSize();
            ui.printAddedMessage(taskDeadline, numberOfTasks);
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
