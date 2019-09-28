public class Task {

	protected String description;
	protected boolean isDone;

	/**
	 * Creates a task with a description which is to be added
	 * to the task list.
	 *
	 * @param description Description of task.
	 */
	public Task(String description) {
		this.description = description;
		this.isDone = false;
	}

	/**
	 * Returns a icon to let the user know that
	 * the tasks has been labelled 'done'.
	 *
	 * @return An icon.
	 */
	public String getStatusIcon() {
		return (isDone ? "x" : " "); //return tick or X symbols
	}

	/**
	 * Changes the boolean from false to true to mark
	 * the task as completed.
	 */
	public void markAsDone() {
		this.isDone = true;
	}

	/**
	 * Returns the description of the task.
	 *
	 * @return Description of the task.
	 */
	public String getDescription() {

		return this.description;
	}

	/**
	 * Returns an integer 1 or 0 to indicate
	 * whether the task is completed when the task list is
	 * being written to a text document.
	 *
	 * @return 1 or 0 to indicate completion of task.
	 */
	public int getStatus() {
		return isDone ? 1 : 0;
	}

	/**
	 * Returns the status icon as well as the description of the task.
	 *
	 * @return String of the task.
	 */
	@Override
	public String toString() {
		return "[" + this.getStatusIcon() + "] " + this.description;
	}
}
