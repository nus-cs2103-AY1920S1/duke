package weomucat.duke.task;

import weomucat.duke.exception.InvalidParameterException;

public class TodoTask extends Task {
	public TodoTask(String description) throws InvalidParameterException {
		super(description);

		if (description.equals("")) {
			throw new InvalidParameterException("The description of a todo cannot be empty.");
		}
	}

	@Override
	public String toString() {
		return String.format("[T]%s", super.toString());
	}
}
