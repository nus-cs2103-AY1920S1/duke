public class DeadLine extends Task {
	private String endDate;

	public DeadLine(String taskName, String endDate) {
		super(taskName);
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + endDate + ")";
	}

	public String getArchivalText() {
		return ("D" + " | " + super.getDoneStatusAsInt() + " | "  + super.getTaskName() + " | " + endDate);
	}
}
