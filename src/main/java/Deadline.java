/**
 * Creates a deadline for a task which has to be done. A sub class of Task.
 */
public class Deadline extends Task {
	private String by;

	public Deadline(String description, String by) {
		super(description);
		this.by = by;
	}

	/**
	 * Returns the date and/or time of the deadline after it has been formatted.
	 *
	 * @return Formatted time.
	 */
	public String getDeadline() {

		return by;
	}



	/**
	 * Returns a string with a deadline symbol [D] as well as the description and deadline of the
	 * task.
	 *
	 * @return String of the task and deadline.
	 */
	@Override
	public String toString() {

		return "[D]" + super.toString() + " (by: " + DateAndTime.checkTime(by) + ")";
	}
}
