package weomucat.duke.task;

import weomucat.duke.exception.InvalidParameterException;

/**
 * An event is a special task that has a location.
 */
public class EventTask extends Task {
	private String at;

	/**
	 * @param description a description of the event
	 * @param at location of the event
	 * @throws InvalidParameterException If the description is empty or at is empty.
	 */
	public EventTask(String description, String at) throws InvalidParameterException {
		super(description);

		if (description.equals("")) {
			throw new InvalidParameterException("The description of a todo cannot be empty.");
		}

		if (at.equals("")) {
			throw new InvalidParameterException("The location of an event cannot be empty.");
		} else {
			this.at = at;
		}
	}

	@Override
	public String toString() {
		return String.format("[E]%s (at: %s)", super.toString(), this.at);
	}
}
