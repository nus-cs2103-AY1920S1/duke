package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.calendar.Date;
import duke.calendar.Time;
import duke.exception.DukeException;
import duke.task.Event;

public class AddEventCommand extends Command {

	String details;

	public AddEventCommand(String details) {
		super();
		this.details = details;
	}

	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		String[] taskDetails = details.split("/at");
		if (details.length() == 0 || taskDetails[0].trim().length() == 0) {
			throw new DukeException("\u2639 OOPS!!! The description of an event cannot be empty.");
		}
		if (taskDetails.length < 2 || taskDetails[1].trim().length() == 0) {
			throw new DukeException("\u2639 OOPS!!! The date/time of an event cannot be empty.");
		}
		String taskDescription = taskDetails[0].trim();
		String[] taskSpecifics = taskDetails[1].trim().split("/to");
		if (taskSpecifics[0].trim().length() == 0) {
			throw new DukeException("\u2639 OOPS!!! The starting date/time of an event cannot be empty.");
		} else {
			String[] rawStarts = taskSpecifics[0].trim().split(" ");
			String rawStartDate = rawStarts[0];
			String rawStartTime = null;
			if (rawStarts.length >= 2 && rawStarts[1].trim().length() != 0) {
				rawStartTime = rawStarts[1];
			}
			String rawEndDate = null;
			String rawEndTime = null;
			if (taskSpecifics.length > 1) {
				String[] rawEnds = taskSpecifics[1].trim().split(" ");
				rawEndDate = rawEnds[0];
				if (rawEnds.length >= 2 && rawEnds[1].trim().length() != 0) {
					rawEndTime = rawEnds[1];
				}
			}
			Date startDate = new Date(rawStartDate);
			Time startTime = new Time(rawStartTime);
			Date endDate = new Date(rawEndDate);
			Time endTime = new Time(rawEndTime);
			Event event = new Event(taskDescription, startDate, startTime, endDate, endTime);
			tasks.addTask(event);
			ui.printAddTaskMessage(event, tasks.getSize());
			try {
				storage.writeToFile(tasks);
			} catch (DukeException exception) {
				ui.printExceptionMessage(exception);
			}
		}
	}

	public boolean isExit() {
		return false;
	}

}
