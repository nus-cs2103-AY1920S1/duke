public class DeadlineTask extends Task {
	private final String deadline;

	public DeadlineTask(String task, String deadline) {
		super(task);
		this.deadline = deadline;
	}

	@Override
	protected String getTypeMarker() {
		return "[D]";
	}

	@Override
	public String toString() {
		String baseDescription = super.toString();
		return String.format("%s (by: %s)", baseDescription, deadline);
	}
}
