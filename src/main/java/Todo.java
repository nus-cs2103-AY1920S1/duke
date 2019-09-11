import java.io.IOException;

/**
 * Creates a todo task which will be added to the task list.
 */
public class Todo extends Task {

	public Todo(String description) {
		super(description);
	}

	/**
	 * Creates a new Todo task based on the input of the user.
	 *
	 * @param input Input string from the user.
	 * @return Todo task.
	 * @throws DukeException
	 */
	public static Todo createToDo(String input) throws DukeException {
		if (input.length() < 6) {
			throw new DukeException(Ui.EMPTY_INPUT);
		}
		String taskToDo = input.substring(5);
		Todo newToDo = new Todo(taskToDo);
		return newToDo;
	}

	public String execute(Todo task, DukeWriteFile storage, TaskList tasks) throws IOException {
		StringBuilder printTask = new StringBuilder();
		printTask.append(Ui.BORDER + "\nGot it. I've added this task:\n");
		printTask.append(task.toString() + "\n");
		printTask.append("Now you have " + tasks.getCounter() + " tasks in the list.\n" + Ui.BORDER);
		storage.appendToFile("T~" + task.getStatus() + "~" +
				                task.getDescription() + "\n");
		return printTask.toString();
	}

	/**
	 * Returns a string with the todo symbol [T] together with the description.
	 *
	 * @return Description of the task.
	 */
	@Override
	public String toString() {
		return "[T]" + super.toString();
	}
}
