public class Deadline extends Task {

	protected String by;

	public Deadline(String description, String by) {
		super(description);
		this.by = by;
	}

	/**
	 * Returns the date and/or time of the deadline
	 * after it has been formatted.
	 *
	 * @return Formatted time.
	 */
	public String getDeadline() {
		return checkTime(by);
	}


	/**
	 * Converting of the date and/or time of the deadline
	 * to the required format.
	 *
	 * @param time Date/time of the deadline.
	 * @return Formatted date/time.
	 */
	public String checkTime(String time) {
		DateAndTime dateTime = new DateAndTime();
		String[] timeAndDate = time.split(" ");
		String formatDeadline = "";
		for (int i = 0; i < timeAndDate.length; i++) {
			String[] date = timeAndDate[i].split("/");
			if (date.length == 3) {
				if (i == 0) {
					formatDeadline += dateTime.formatDate(timeAndDate[i] + ", ");
				} else {
					formatDeadline += ", " + dateTime.formatDate(timeAndDate[i]);
				}

			} else if (timeAndDate[i].length() == 4) {
				if (i == 0) {
					formatDeadline += dateTime.formatTime(timeAndDate[i]);
				} else {
					formatDeadline += " " + dateTime.formatTime(timeAndDate[i]);
				}

			} else {

				formatDeadline += timeAndDate[i] + " ";
			}

		}

		return formatDeadline;

	}


	/**
	 * Returns a string with a deadline symbol [D] as well as
	 * the description and deadline of the task.
	 *
	 * @return String of the task and deadline.
	 */
	@Override
	public String toString() {

		return "[D]" + super.toString() + " (by: " + checkTime(by) + ")";
	}
}
