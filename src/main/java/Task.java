public class Task {
	private boolean taskCompletionStatus;
	private String taskDescription;

	public Task(String description) {
		taskCompletionStatus = false;
		taskDescription = description; 
	}

	public Task complete() {
		taskCompletionStatus = true;
		return this;
	}

	@Override
	public String toString() {
		return "[" + (taskCompletionStatus ? "✓" : "✗") + "] " + taskDescription;
	}
}
