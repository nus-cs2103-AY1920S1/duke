package weomucat.duke;

import java.util.HashMap;

public abstract class TodoCommand implements Command {
	private String description;

	@Override
	public String[] getParameterOptions() {
		return new String[0];
	}

	@Override
	public void setParameters(HashMap<String, String> parameters) {
		description = parameters.get(PARAMETER_DEFAULT);
	}

	@Override
	public void run() throws DukeException {
		TodoTask task = new TodoTask(description);
		updateListeners(task);
	}

	public abstract void updateListeners(Task task) throws DukeException;
}
