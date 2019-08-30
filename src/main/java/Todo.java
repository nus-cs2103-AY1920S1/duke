public class Todo extends Task {

	public Todo(String description) {

		super(description);
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
