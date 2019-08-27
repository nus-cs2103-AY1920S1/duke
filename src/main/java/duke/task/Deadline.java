package duke.task;

import duke.calendar.Date;
import duke.calendar.Time;

/**
 * Represents a <code>Task</code> with a deadline. A <code>Deadline</code> object corresponds to a description about
 * the <code>Task</code> and the <code>Date</code> and optionally, the <code>Time</code> at which it is due.
 */
public class Deadline extends Task {
	protected Date date;
	protected Time time;

	/**
	 * Constructor for <code>Deadline</code>.
	 * @param description Details about the <code>Deadline</code> task.
	 * @param date Date at which the task is due.
	 * @param time Time at which the task is due.
	 */
	public Deadline(String description, Date date, Time time) {
		super(description);
		this.date = date;
		this.time = time;
	}

	/**
	 * Returns the date at which the task is due.
	 * @return <code>Date</code> at which the task is due.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Returns the time at which the task is due.
	 * @return <code>Time</code> at which the task is due.
	 */
	public Time getTime() {
		return time;
	}

	/**
	 * Returns the first letter of the task type.
	 * @return "D".
	 */
	public String getType() {
		return "D";
	}

	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + date + (time.isNull() ? "" : ", " + time) + ")";
	}
}
