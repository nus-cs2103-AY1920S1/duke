package duke.tasklist;

public class Task {
	public boolean taskCompletionStatus;
	private String taskDescription;

	public Task(String description) {
		taskCompletionStatus = false;
		taskDescription = description;
	}

	public Task complete() {
		taskCompletionStatus = true;
		return this;
	}

	public boolean isComplete() {
		return taskCompletionStatus;
	}

	public String getDescription() {
		return new String(taskDescription);
	}

	@Override
	public String toString() {
		return new StringBuilder("[")
				.append((taskCompletionStatus ? "✓" : "✗"))
				.append("] ")
				.append(taskDescription)
				.toString();
	}
}
