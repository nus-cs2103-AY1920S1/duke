public class ToDo extends Task {
	public ToDo(String description) {
		super(description);
	}

	public ToDo(boolean isComplete, String description) {
		super(description);
		taskCompletionStatus = isComplete;
	}

	@Override
	public String toString() {
		return "[T]" + super.toString();
	}
}
