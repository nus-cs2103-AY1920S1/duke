public class Task {
	private String taskName;
	private boolean doneStatus;

	public Task(String taskName) {
		this.taskName = taskName;
		this.doneStatus = false;
	}

	public void markCompleted() {
		this.doneStatus = true;
	}

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
}
