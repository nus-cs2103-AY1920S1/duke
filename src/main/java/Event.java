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

		return checkTime(at);
	}

	/**
	 * Formats the date and/or time of the event to the
	 * specified format.
	 *
	 * @param time Date/time of the event.
	 * @return The formatted time/date.
	 */
	public String checkTime(String time) {
		DateAndTime eventDateTime = new DateAndTime();
		String[] timeAndDate = time.split(" ");
		String formatDeadline = "";
		for (int i = 0; i < timeAndDate.length; i++) {
			String[] date = timeAndDate[i].split("/");
			if (date.length == 3) {
				if (i == 0) {
					formatDeadline += eventDateTime.formatDate(timeAndDate[i] + ", ");
				} else {
					formatDeadline += ", " + eventDateTime.formatDate(timeAndDate[i]);
				}

			} else if (timeAndDate[i].length() == 4) {
				if (i == 0) {
					formatDeadline += eventDateTime.formatTime(timeAndDate[i]);
				} else {
					formatDeadline += " " + eventDateTime.formatTime(timeAndDate[i]);
				}

			} else {
				formatDeadline += timeAndDate[i] + " ";
			}

		}

		return formatDeadline;
	}


	/**
	 * Returns a string with the event symbol [E] as well as the
	 * description and date/time of the event.
	 *
	 * @return String of the event and date/time.
	 */
	@Override
	public String toString() {

		return "[E]" + super.toString() + " (at: " + checkTime(at) + ")";
	}
}
