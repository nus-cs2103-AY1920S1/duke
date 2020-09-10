package duke.task;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {
	
	/**
	 * Constructor to create a ToDo task object.
	 *
	 * @param taskType        Character of first letter of task.
	 * @param taskDescription String input of command from user.
	 * @param isDone          Boolean indicating if task is completed.
	 */
	public ToDo(char taskType, String taskDescription, boolean isDone) {
		super(taskType, taskDescription, isDone);
	}
	
	/**
	 * Constructor to create a ToDo task object.
	 *
	 * @param taskDescription String input of command from user.
	 * @param isDone          Boolean indicating if task is completed.
	 */
	public ToDo(String taskDescription, boolean isDone) {
		super(taskDescription, isDone);
	}
	
}
