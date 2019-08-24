public class Event extends Task {

	protected Date startDate;
	protected Time startTime;
	protected Date endDate;
	protected Time endTime;

	public Event(String description, Date startDate, Time startTime, Date endDate, Time endTime) {
		super(description);
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
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
