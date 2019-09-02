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
	 * Formats the date and/or time of the event to the
	 * specified format.
	 *
	 * @param time Date/time of the event.
	 * @return The formatted time/date.
	 */
	public String checkTime(String time) {
		DateAndTime eventDateTime = new DateAndTime();
		String[] timeAndDate = time.split(" ");
		String formatEvent = "";
		for (int i = 0; i < timeAndDate.length; i++) {
			String[] date = timeAndDate[i].split("/");
			if (date.length == 3) {
				if (i == 0) {
					formatEvent += eventDateTime.formatDate(timeAndDate[i]) + ", ";
				} else if (i == timeAndDate.length - 1) {
					formatEvent += eventDateTime.formatDate(timeAndDate[i]);
				} else {
					formatEvent += eventDateTime.formatDate(timeAndDate[i]) + ", ";
				}
			} else if (timeAndDate[i].length() == 4) {
				if (i == 0) {
					formatEvent += eventDateTime.formatTime(timeAndDate[i]) + ", ";
				} else if (i == timeAndDate.length - 1) {
					formatEvent += eventDateTime.formatTime(timeAndDate[i]);
				} else {
					formatEvent += eventDateTime.formatTime(timeAndDate[i]) + ", ";
				}
			} else {
				if (i == 0 || i != timeAndDate.length - 1) {
					formatEvent += timeAndDate[i] + " ";
				}

			}

		}

		return formatEvent;
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
