public class Task {

	protected String description;
	protected boolean isDone;

	public Task(String description) {

		this.description = description;
		this.isDone = false;
	}

	public String getStatusIcon() {

		return (isDone ? "x" : " "); //return tick or X symbols
	}

	public void markAsDone(){

		this.isDone = true;
	}

	public void markNotDone(){

		this.isDone = false;
	}

	public String getDescription() {

		return this.description;
	}

	public int getStatus() {
		return isDone ? 1 : 0;
	}

	@Override
	public String toString(){

		return "[" + this.getStatusIcon() + "] " + this.description;
	}
}
