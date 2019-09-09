import java.time.LocalDateTime;

/**
 * Represents the ToDo task which is one of the 3 types of tasks.
 */
public class ToDo extends Task {
	/**
	 * Constructor for ToDo object
	 * @param taskName the name of the todo task
	 */
	public ToDo(String taskName) {
		super(taskName);
	}

	/**
	 * converts the ToDo object into a nice string representation for displaying to the user
	 * @return a string representation of the todo object
	 */
	@Override
	public String toString() {
		return "[T]" + super.toString();
	}

	/**
	 * converts the ToDo object into a string representation in the archival form
	 * @return a string represnetation of the todo object in archival form
	 */
	public String getArchivalText() {
		return ("T" + " | " + super.getDoneStatusAsInt() + " | "  + super.getTaskName());
	}

	@Override
	public LocalDateTime getDateTime() {
		return null;
	}
}
