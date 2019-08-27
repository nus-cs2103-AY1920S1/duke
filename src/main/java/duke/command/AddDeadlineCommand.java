package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.calendar.Date;
import duke.calendar.Time;
import duke.exception.DukeException;
import duke.task.Deadline;

/**
 * Represents a <code>Command</code> that adds a new <code>Deadline</code> object to the <code>TaskList</code>.
 */
public class AddDeadlineCommand extends Command {

	String details;

	/**
	 * Constructor for <Code>AddDeadlineCommand</Code>.
	 * @param details The unprocessed details of the <code>Deadline</code> task.
	 */
	public AddDeadlineCommand(String details) {
		super();
		this.details = details;
	}

	/**
	 * Creates a  new <code>Deadline</code> object and adds it to the <code>TaskList</code>.
	 * Calls methods in <code>Storage</code> and <code>Ui</code> to write the updated <code>TaskList</code> to hard
	 * disk and print message to console respectively.
	 * @param tasks Instance of <code>TaskList</code> that stores <code>Task</code> objects.
	 * @param ui Instance of <code>Ui</code> that handles user input and output.
	 * @param storage Instance of <code>Storage</code> that handles writing and loading of information to hard disk.
	 * @throws DukeException If provided details are insufficient or invalid.
	 */
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		String[] taskDetails = details.split("/by");
		if (details.length() == 0 || taskDetails[0].trim().length() == 0) {
			throw new DukeException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
		}
		if (taskDetails.length < 2 || taskDetails[1].trim().length() == 0) {
			throw new DukeException("\u2639 OOPS!!! The date/time of a deadline cannot be empty.");
		}
		String taskDescription = taskDetails[0].trim();
		String[] taskSpecifics = taskDetails[1].trim().split(" ");
		String rawTaskDate = taskSpecifics[0];
		String rawTaskTime;
		if (taskSpecifics.length < 2 || taskSpecifics[1].trim().length() == 0) {
			rawTaskTime = null;
		} else {
			rawTaskTime = taskSpecifics[1];
		}
		Date taskDate = new Date(rawTaskDate);
		Time taskTime = new Time(rawTaskTime);
		Deadline deadline = new Deadline(taskDescription, taskDate, taskTime);
		tasks.addTask(deadline);
		ui.printAddTaskMessage(deadline, tasks.getSize());
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
		} else if (o instanceof AddDeadlineCommand) {
			AddDeadlineCommand addDeadlineCommand = (AddDeadlineCommand) o;
			return addDeadlineCommand.getDetails().equals(details);
		} else {
			return false;
		}
	}

}
