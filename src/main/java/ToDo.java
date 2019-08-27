public class ToDo extends Task {
	public ToDo(String taskName) {
		super(taskName);
	}

	@Override
	public String toString() {
		return "[T]" + super.toString();
	}

	public String getArchivalText() {
		return ("T" + " | " + super.getDoneStatusAsInt() + " | "  + super.getTaskName());
	}
}
