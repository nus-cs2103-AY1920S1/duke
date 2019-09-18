import java.io.IOException;

/**
 * Creates a deadline for a task which has to be done. A sub class of Task.
 */
public class Deadline extends Task {
	private String by;

	public Deadline(String description, String by) {
		super(description);
		this.by = by;
	}

	/**
	 * Creates a deadline task which will be added into the tasklist.
	 *
	 * @param input Description of the task to be added.
	 * @return Deadline task.
	 * @throws DukeException
	 */
	public static Deadline createDeadLine(String input) throws DukeException {
		if (input.length() < 9) {
			throw new DukeException(Ui.EMPTY_INPUT);
		}

		String[] deadLineDate = input.substring(9).split(" /by ");
		if (deadLineDate.length != 2) {
			throw new DukeInvalidArgumentException(Ui.DEADLINE_FORMAT,
			                                       input);
		}
		String taskD = deadLineDate[0];
		String dateD = deadLineDate[1];
		Deadline newDeadLine = new Deadline(taskD, dateD);
		return newDeadLine;
	}

	/**
	 * Returns the date and/or time of the deadline after it has been formatted.
	 *
	 * @return Formatted time.
	 */
	public String getDeadline() {
		return by;
	}

	/**
	 * Executes the deadline command which adds a new deadline task to the
	 * tasklist as well as writing it to the storage.
	 *
	 * @param task    Deadline task to be added to the tasklist.
	 * @param storage Storage of the tasklist.
	 * @param tasks   Tasklist.
	 * @return A string of the task that has been added in.
	 * @throws IOException
	 */
	public String execute(Deadline task, DukeWriteFile storage, TaskList tasks) throws IOException {
		StringBuilder printTask = new StringBuilder();
		printTask.append(Ui.BORDER + "\nGot it. I've added this task:\n");
		printTask.append(task.toString() + "\n");
		printTask.append("Now you have " + tasks.getCounter() + " tasks in the list.\n" + Ui.BORDER);
		storage.appendToFile("E~" + task.getStatus() + "~" +
				                     task.getDescription() + "~" + task.getDeadline() + "\n");
		return printTask.toString();
	}

	/**
	 * Returns a string with a deadline symbol [D] as well as the description and deadline of the
	 * task.
	 *
	 * @return String of the task and deadline.
	 */
	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + DateAndTime.checkTime(by) + ")";
	}
}
