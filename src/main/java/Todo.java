// Adapted from https://nus-cs2103-ay1920s1.github.io/website/schedule/week2/project.html 

/**
 * Class representation of a Todo in the list.
 */
public class Todo extends Task {	
	/**
	 * @param description A string description of this Todo
	 */
	public Todo(String description) {
		super(description);
	}

	/**
	 * @return A string representation of this Todo
	 */
	@Override
	public String toString() {
		return "[T]" + super.toString();
	}
}