package weomucat.duke;

public class DeadlineTask extends Task {
	private String by;

	public DeadlineTask(String description, String by) {
		super(description);
		this.by = by;
	}

	@Override
	public String toString() {
		return String.format("[D]%s (by: %s)", super.toString(), this.by);
	}
}
