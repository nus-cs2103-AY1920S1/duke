public class Event extends Task{

	private String datetime;

	public Event(String taskName, String datetime) {
		super(taskName);
		this.datetime = datetime;
	}

	@Override
	public String getType() {
		return "E";
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	@Override
	public String toString() {
		return String.format("[%s][%s] %s (at: %s)",
				this.getType() ,isDone ? "\u2713": "\u2718",
				this.taskName, this.datetime);
	}
}
