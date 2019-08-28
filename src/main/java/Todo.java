// Adapted from https://nus-cs2103-ay1920s1.github.io/website/schedule/week2/project.html 

public class Todo extends Task {	
	public Todo(String description) {
		super(description);
	}

	@Override
	public String toString() {
		return "[T]" + super.toString();
	}
}