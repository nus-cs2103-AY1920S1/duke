package weomucat.duke.command;

import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.InvalidParameterException;

import java.util.HashMap;

public abstract class DoneCommand implements Command {
	private int i;

	@Override
	public String[] getParameterOptions() {
		return new String[0];
	}

	@Override
	public void setParameters(HashMap<String, String> parameters) throws DukeException {
		String index = parameters.get(PARAMETER_DEFAULT);

		// Get index of task
		try {
			this.i = Integer.parseInt(index) - 1;
		} catch (NumberFormatException e) {
			throw new InvalidParameterException("The index is not a valid number.");
		}
	}

	@Override
	public void run() throws DukeException {
		updateListeners(i);
	}

	public abstract void updateListeners(int i) throws DukeException;
}
