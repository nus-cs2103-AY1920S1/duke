public class Deadline extends Task {

	protected Date date;
	protected Time time;

	public Deadline(String description, Date date, Time time) {
		super(description);
		this.date = date;
		this.time = time;
	}

	public Date getDate() {
		return date;
	}

	public Time getTime() {
		return time;
	}

	public String getType() {
		return "D";
	}

	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + date + (time.isNull() ? "" : ", " + time) + ")";
	}

}
