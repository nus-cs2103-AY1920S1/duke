package weomucat.duke.command;

import weomucat.duke.exception.DukeException;
import weomucat.duke.task.DeadlineTask;
import weomucat.duke.task.Task;

import java.util.HashMap;

public abstract class DeadlineCommand implements Command {
	private String description;
	private String by;

	@Override
	public String[] getParameterOptions() {
		return new String[]{PARAMETER_BY};
	}

	@Override
	public void setParameters(String body, HashMap<String, String> parameters) {
		this.description = body;
		this.by = parameters.get(PARAMETER_BY);
	}

	public void run() throws DukeException {
		DeadlineTask task = new DeadlineTask(this.description, this.by);
		updateListeners(task);
	}

	/**
	 * Listeners to update when this Command is run.
	 * @param task deadline task this Command produces
	 * @throws DukeException If there is anything wrong with processing.
	 */
	public abstract void updateListeners(Task task) throws DukeException;
}
