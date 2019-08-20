public class Deadline extends Task {
	protected String by;

	public Deadline(String description, String by) {
		super(description);
		try {
			this.by = Parser.parseDateTime(by);
		} catch (Exception e) {
			this.by = by;
		}
	}

	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + by + ")";
	}
}
