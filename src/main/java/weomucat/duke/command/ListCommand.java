package weomucat.duke.command;

import weomucat.duke.exception.DukeException;

import java.util.HashMap;

public abstract class ListCommand implements Command {
	@Override
	public String[] getParameterOptions() {
		return new String[0];
	}

	@Override
	public void setParameters(String body, HashMap<String, String> parameters) throws DukeException {

	}

	@Override
	public void run() throws DukeException {
		updateListeners();
	}

	/**
	 * Listeners to update when this Command is run.
	 * @throws DukeException If there is anything wrong with processing.
	 */
	public abstract void updateListeners() throws DukeException;
}
