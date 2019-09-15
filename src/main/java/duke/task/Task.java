package duke.task;

/**
 * Represents an task.
 * Has functions to print the task information to a standard form,
 * to set and get the given time of the deadline.
 */
public class Task {
	protected char taskType;
	protected String taskDescription;
	protected boolean isDone;
	
	/**
	 * Constructor to create Task object.
	 */
	public Task() {
		this.taskDescription = "";
		this.isDone = false;
	}
	
	/**
	 * Constructor to create Task object.
	 *
	 * @param taskDescription String input of command from user.
	 * @param isDone          Boolean indicating if task is completed.
	 */
	public Task(String taskDescription, boolean isDone) throws NullPointerException {
		this.isDone = isDone;
		
		if (taskDescription == null) {
			throw new NullPointerException();
		}
		this.taskDescription = taskDescription;
	}
	
	/**
	 * Constructor to create Event task object.
	 *
	 * @param taskDescription String input of command from user.
	 * @param isDone          Boolean indicating if task is completed.
	 * @param taskType        Character of first letter of task.
	 */
	public Task(char taskType, String taskDescription, boolean isDone) throws NullPointerException {
		this.isDone = isDone;
		this.taskType = taskType;
		
		if (taskDescription == null) {
			throw new NullPointerException();
		}
		this.taskDescription = taskDescription;
	}
	
	/**
	 * Returns string representation of task,
	 * in terms of initial, icon of done, task description.
	 *
	 * @return String representation of task.
	 */
	public String printTask() {
		return "[" + getFirstCharTask() + "][" + getIcon() + "] " + getTaskDescription();
	}
	
	/**
	 * Returns char of first letter in task name.
	 *
	 * @return char of first letter in task name.
	 */
	public char getFirstCharTask() {
		return taskType;
	}
	
	/**
	 * Returns char icon.
	 * Returns tick if isDone is true, else returns cross.
	 *
	 * @return char icon.
	 */
	public char getIcon() {
		if (isDone) {
			return 'Y';
		}
		return 'N';
	}
	
	/**
	 * Get task description for task.
	 *
	 * @return String task description.
	 */
	public String getTaskDescription() {
		return taskDescription;
	}
	
	/**
	 * Indicate that task is done.
	 * Set isDone true when function is called.
	 */
	public void setIsDone() {
		this.isDone = true;
	}
	
	/**
	 * Get isDone,
	 * indicating done status of the task.
	 *
	 * @return boolean true if done, else false.
	 */
	public boolean getIsDone() {
		return isDone;
	}
	
}

