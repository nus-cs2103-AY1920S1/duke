package duke.tasklist;

import duke.DukeException;
import duke.io.Parser;

public class Event extends Task {
	public String time;

	public Event(String description, String time) {
		super(description);
		try {
			this.time = Parser.parseDateTime(time);
		} catch (DukeException e) {
			this.time = time;
		}
	}

	public Event(boolean isComplete, String description, String time) {
		super(description);
		try {
			this.time = Parser.parseDateTime(time);
		} catch (DukeException e) {
			this.time = time;
		}
		taskCompletionStatus = isComplete;
	}

	@Override
	public String toString() {
		return new StringBuilder("[E]")
				.append(super.toString())
				.append(" (at: ")
				.append(time)
				.append(")")
				.toString();
	}

}
