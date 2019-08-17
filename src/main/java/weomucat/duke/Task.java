package weomucat.duke;

public class Task {
	private String description;
	private boolean done;

	public Task(String description) {
		this.description = description;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		String done_icon = this.done ? "[\u2713]" : "[\u2718]";
		return String.format("%s %s", done_icon, this.description);
	}
}
