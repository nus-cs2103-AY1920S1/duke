public class Deadline extends Task {

	private String datetime;

	public Deadline(String taskName, String datetime) {
		super(taskName);
		this.datetime = datetime;
	}

	@Override
	public String getType() {
		return "D";
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
}
