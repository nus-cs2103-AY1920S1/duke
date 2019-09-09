import java.time.LocalDateTime;

/**
 * Represents a single task in the program. Specifically has 3 types of tasks that inherit from this class:
 * 		todo, deadline and event
 */
public abstract class Task {
	private String taskName;
	private boolean doneStatus;

	/**
	 * Constructor for all task objects. Also initializes the doneStatus to false.
	 * @param taskName the name of the task object
	 */
	public Task(String taskName) {
		this.taskName = taskName;
		this.doneStatus = false;
	}

	/**
	 * marks this task as completed by setting the doneStatus to true.
	 */
	public void markCompleted() {
		this.doneStatus = true;
	}

	/**
	 * marks this task as incomplete by setting the doneStatus to false.
	 */
	public void markIncomplete() { this.doneStatus = false;}

	/**
	 * Converts the task object to include the completion status. Adds a nice tick and cross based on the doneStatus
	 * @return a string representation of the task object
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (doneStatus == true) {
			sb.append("[✓]");
		} else {
			sb.append("[✗]");
		}
		sb.append(" ");
		sb.append(taskName);
		return sb.toString();
	}

	/**
	 * get the name of the task object
	 * @return the task name of the object
	 */
	public String getTaskName() {
		return this.taskName;
	}

	/**
	 * an abstract method to be implemented in child classes to convert the task into archival format
	 * @return a string represntation of the task for archival purposes
	 */
	public abstract String getArchivalText();

	/**
	 * getDoneStatusAsInt converts the doneStatus from boolean to int. 1 for completed and 0 for incomplete. Used
	 * 		in converting the object into archival format
	 * @return
	 */
	public String getDoneStatusAsInt() {
		if (doneStatus) {
			return "1";
		} else {
			return "0";
		}
	}

	public abstract LocalDateTime getDateTime();
}
