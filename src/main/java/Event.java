import java.io.IOException;

/**
 * Creates an event which will be added to the task list for reminders.
 */
public class Event extends Task {

	protected String at;

	public Event(String description, String at) {

		super(description);
		this.at = at;
	}

	/**
	 * Returns the formatted date and/or time of the event.
	 *
	 * @return Formatted string of the date and/or time.
	 */
	public String getVenue() {
		return at;
	}

	/**
	 * Creates a event task with the description and the time/venue of the event.
	 * @param input Description of the event.
	 * @return Event task to be added into the tasklist.
	 * @throws DukeException
	 */
	public static Event createEvent(String input) throws DukeException {
		if (input.length() < 6) {
			throw new DukeException(Ui.EMPTY_INPUT);
		}
		String[] eventDate = input.substring(6).split(" /at ");
		assert eventDate.length == 2: "Testing Wrong input";
		if (eventDate.length != 2) {
			throw new DukeInvalidArgumentException(Ui.EVENT_FORMAT,
			                                       input);
		}
		String taskE = eventDate[0];
		String dateE = eventDate[1];
		Event newEvent = new Event(taskE, dateE);
		return newEvent;
	}

	/**
	 * Executes the event command which displays the description of the event created.
	 * @param task Event task to be added into the tasklist.
	 * @param storage Storage of the tasklist.
	 * @param tasks Tasklist.
	 * @return A string of the task that has been added into the tasklist.
	 * @throws IOException
	 */
	public String execute(Event task, DukeWriteFile storage, TaskList tasks) throws IOException {
		StringBuilder printTask = new StringBuilder();
		printTask.append(Ui.BORDER + "\nGot it. I've added this task:\n");
		printTask.append(task.toString() + "\n");
		printTask.append("Now you have " + tasks.getCounter() + " tasks in the list.\n" + Ui.BORDER);
		storage.appendToFile("D~" + task.getStatus() + "~" +
				                task.getDescription() + "~" + task.getVenue() + "\n");
		return printTask.toString();
	}

	/**
	 * Returns a string with the event symbol [E] as well as the
	 * description and date/time of the event.
	 *
	 * @return String of the event and date/time.
	 */
	@Override
	public String toString() {
		return "[E]" + super.toString() + " (at: " + DateAndTime.checkTime(at) + ")";
	}
}
