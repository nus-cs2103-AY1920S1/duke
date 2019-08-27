package weomucat.duke.task;

import java.io.Serializable;

public abstract class Task implements Serializable {
	private String description;
	private boolean done;

	public Task(String description) {
		this.description = description;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	/**
	 * @return description of this Task
	 */
	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		String done_icon = this.done ? "[\u2713]" : "[\u2718]";
		return String.format("%s %s", done_icon, this.description);
	}
}
