package weomucat.duke;

public class EventTask extends Task {
	private String at;

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
