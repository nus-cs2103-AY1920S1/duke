package duke.tasklist;

/**
 * Class representing a to do task which can be added to the task list
 */
public class ToDo extends Task {

	/**
	 * Constructs the todo task, initially incomplete
	 * @param description The description of the task
	 */
	public ToDo(String description) {
		super(description);
	}

	/**
	 * Constructs the todo task, with the completion status provided
	 * @param isComplete The completion status of the task
	 * @param description The description of the task
	 */
	public ToDo(boolean isComplete, String description) {
		super(description);
		taskCompletionStatus = isComplete;
	}

	/**
	 * Returns the string representation of the todo task
	 * @return The string representation of the todo task
	 */
	@Override
	public String toString() {
		return "[T]".concat(super.toString());
	}
}
