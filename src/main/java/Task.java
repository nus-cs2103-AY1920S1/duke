public class Task {
	private final String description;
	private boolean completed;

	public Task(String description) {
		this.description = description;
		this.completed = false;
	}

	public void markComplete() {
		this.completed = true;
	}

	public boolean isCompleted() {
		return this.completed;
	}

	private static final String completedMarker = "[✓]";
	private static final String incompleteMarker = "[✗]";

	protected String getTypeMarker() {
		return "[T]";
	}

	private String getCompleteMarker() {
		return this.completed ? completedMarker : incompleteMarker;
	}

	@Override
	public String toString() {
		return this.getTypeMarker() + this.getCompleteMarker() + ' ' + this.description;
	}
}
