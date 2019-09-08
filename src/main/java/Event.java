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

	public static Event createEvent(String input) throws DukeException {
		if (input.length() < 6) {
			throw new DukeException(Ui.EMPTY_INPUT);
		}
		String[] eventDate = input.substring(6).split(" /at ");
		if (eventDate.length != 2) {
			throw new DukeInvalidArgumentException("OOPS!! Wrong format! Format: event [Task] /at [time]",
			                                       input);
		}
		String taskE = eventDate[0];
		String dateE = eventDate[1];
		Event newEvent = new Event(taskE, dateE);
		return newEvent;
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
