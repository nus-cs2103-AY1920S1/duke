package duke.task;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a deadline task.
 * Has functions to print the task information to a standard form,
 * to set and get the given time of the deadline.
 */
public class Deadline extends Task {
	private Date givenTime;
	private SimpleDateFormat dateFormatting;
	
	/**
	 * Constructor to create Deadline task object.
	 *
	 * @param taskType        Character of first letter of task.
	 * @param taskDescription String input of command from user.
	 * @param isDone          Boolean indicating if task is completed.
	 * @param givenTime       String of given time of deadline.
	 */
	public Deadline(char taskType, String taskDescription, boolean isDone, String givenTime) {
		super(taskType, taskDescription, isDone);
		
		try {
			dateFormatting = new SimpleDateFormat("dd/MM/yyyy HHmm");
			this.givenTime = dateFormatting.parse(givenTime, new ParsePosition(0));
			
		} catch (NullPointerException n) {
			throw new NullPointerException();
		}
	}
	
	/**
	 * Returns string representation of task,
	 * in terms of initial, icon of done, task description and given deadline.
	 *
	 * @return String representation of task.
	 */
	@Override
	public String printTask() {
		StringBuilder taskInformation = new StringBuilder();
		taskInformation.append("[").append(getFirstCharTask()).append("]");
		taskInformation.append("[").append(getIcon()).append("] ");
		taskInformation.append(getTaskDescription()).append(" (by: ").append(getGivenTime()).append(")");
		return taskInformation.toString();
	}
	
	/**
	 * Get time for given deadline.
	 */
	public String getGivenTime() {
		return dateFormatting.format(givenTime);
	}
}
