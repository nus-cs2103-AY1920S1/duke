package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.calendar.Date;
import duke.calendar.Time;
import duke.exception.DukeException;
import duke.task.Deadline;

public class AddDeadlineCommand extends Command {

	String details;

	public AddDeadlineCommand(String details) {
		super();
		this.details = details;
	}

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

	public boolean isExit() {
		return false;
	}

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
