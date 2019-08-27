public abstract class Task {
	private String taskName;
	private boolean doneStatus;

	public Task(String taskName) {
		this.taskName = taskName;
		this.doneStatus = false;
	}

	public void markCompleted() {
		this.doneStatus = true;
	}

	public void markIncomplete() { this.doneStatus = false;}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (doneStatus == true) {
			sb.append("[✓]");
		} else {
			sb.append("[✗]");
		}
		sb.append(" ");
		sb.append(taskName);
		return sb.toString();
	}

	public String getTaskName() {
		return this.taskName;
	}

	public abstract String getArchivalText();

	public String getDoneStatusAsInt() {
		if (doneStatus) {
			return "1";
		} else {
			return "0";
		}
	}
}
