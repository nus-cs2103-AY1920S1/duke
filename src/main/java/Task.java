public class Task {
	protected boolean taskCompletionStatus;
	protected String taskDescription;

	public Task(String description) {
		taskCompletionStatus = false;
		taskDescription = description;
	}

	public void complete() {
		taskCompletionStatus = true;
	}

	@Override
	public String toString() {
		return "[" + (taskCompletionStatus ? "✓" : "✗") + "] " + taskDescription;
	}
}
