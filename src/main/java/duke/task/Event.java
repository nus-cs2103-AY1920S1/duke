package duke.task;

import duke.calendar.Date;
import duke.calendar.Time;

/**
 * Represents a <code>Task</code> that is an event. A <code>Event</code> object corresponds to a description about
 * the <code>Task</code>, the starting <code>Date</code> and optionally, starting <code>Time</code>, ending
 * <code>Date</code> and <code>Time</code> of the event.
 */
public class Event extends Task {

	protected Date startDate;
	protected Time startTime;
	protected Date endDate;
	protected Time endTime;

	/**
	 * Constructor for <code>Event</code>.
	 * @param description Details about the <code>Event</code> task.
	 * @param startDate Date on which the event starts.
	 * @param startTime Time at which the event starts.
	 * @param endDate Date on which the event ends.
	 * @param endTime Time at which the event ends.
	 */
	public Event(String description, Date startDate, Time startTime, Date endDate, Time endTime) {
		super(description);
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
	}

	/**
	 * Returns the starting date of the event.
	 * @return <code>Date</code> on which the event starts.
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Returns the starting time of the event.
	 * @return <code>Time</code> at which the event starts.
	 */
	public Time getStartTime() {
		return startTime;
	}

	/**
	 * Returns the ending date of the event.
	 * @return <code>Date</code> on which the event ends.
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Returns the ending time of the event.
	 * @return <code>Time</code> at which the event end.
	 */
	public Time getEndTime() {
		return endTime;
	}

	/**
	 * Returns the first letter of the task type.
	 * @return "E".
	 */
	public String getType() {
		return "E";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[E]" + super.toString() + " (at: " + startDate + (startTime.isNull() ? "" : ", " + startTime));
		if ((!endTime.isNull()) || (!endDate.isNull())) {
			builder.append(" to");
		}
		if (!endDate.isNull()) {
			builder.append(" " + endDate);
		}
		if (!endTime.isNull()) {
			if (!endDate.isNull()) {
				builder.append(",");
			}
			builder.append(" " + endTime);
		}
		builder.append(")");
		return builder.toString();
	}

}
