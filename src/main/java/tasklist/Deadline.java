package duke.tasklist;

import duke.DukeException;
import duke.io.Parser;

public class Deadline extends Task {
	public String time;

	public Deadline(String description, String time) {
		super(description);
		try {
			this.time = Parser.parseDateTime(time);
		} catch (DukeException e) {
			this.time = time;
		}
	}

	public Deadline(boolean isComplete, String description, String time) {
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
		return new StringBuilder("[D]")
				.append(super.toString())
				.append(" (by: ")
				.append(time)
				.append(")")
				.toString();
	}
}
