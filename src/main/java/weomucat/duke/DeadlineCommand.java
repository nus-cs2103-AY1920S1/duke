package weomucat.duke;

import java.util.HashMap;

public abstract class DeadlineCommand implements Command {
	private String description;
	private String by;

	@Override
	public String[] getParameterOptions() {
		return new String[]{PARAMETER_BY};
	}

	@Override
	public void setParameters(HashMap<String, String> parameters) {
		description = parameters.get(PARAMETER_DEFAULT);
		by = parameters.get(PARAMETER_BY);
	}

	public void run() throws DukeException {
		DeadlineTask task = new DeadlineTask(description, by);
		updateListeners(task);
	}

	public abstract void updateListeners(Task task) throws DukeException;
}
