package weomucat.duke.command;

import weomucat.duke.exception.DukeException;
import weomucat.duke.task.EventTask;
import weomucat.duke.task.Task;

import java.util.HashMap;

public abstract class EventCommand implements Command {
	private String description;
	private String at;

	@Override
	public String[] getParameterOptions() {
		return new String[]{PARAMETER_AT};
	}

	@Override
	public void setParameters(HashMap<String, String> parameters) {
		this.description = parameters.get(PARAMETER_DEFAULT);
		this.at = parameters.get(PARAMETER_AT);
	}

	@Override
	public void run() throws DukeException {
		EventTask task = new EventTask(description, at);
		updateListeners(task);
	}

	public abstract void updateListeners(Task task) throws DukeException;
}
