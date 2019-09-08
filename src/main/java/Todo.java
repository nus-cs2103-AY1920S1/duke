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
