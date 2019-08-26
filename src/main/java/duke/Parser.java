package duke;

import duke.command.*;
import duke.exception.DukeException;

/**
 * Represents a parser that reads and parses raw command strings.
 */
public class Parser {

	/**
	 * Parses raw commands and returns a type of <code>Command</code> correspondingly.
	 * @param rawCommandDetails Details of the command provided by user.
	 * @return A type of <code>Command</code> that corresponds to the raw command.
	 * @throws DukeException If the raw commands are invalid, in the wrong format or are lacking required information.
	 */
	static Command parse(String rawCommandDetails) throws DukeException {
		String commandDetails = rawCommandDetails.replaceAll("\\s+", " ");;
		if (commandDetails.equals("bye")) {
			return new ExitCommand();
		} else if (commandDetails.equals("list")) {
			return new ListCommand();
		} else if (commandDetails.startsWith("delete")) {
			String[] commandSplit = commandDetails.split(" ");
			if (commandSplit.length < 2) {
				throw new DukeException("\u2639 OOPS!!! Please specify a task to delete.");
			}
			int index = Integer.parseInt(commandSplit[1]);
			return new DeleteCommand(index);
		} else if (commandDetails.startsWith("done")) {
			String[] commandSplit = commandDetails.split(" ");
			if (commandSplit.length < 2) {
				throw new DukeException("\u2639 OOPS!!! Please specify the task that has been done.");
			}
			int index = Integer.parseInt(commandSplit[1]);
			return new DoneCommand(index);
		} else {
			String taskDetailsString;
			if (commandDetails.startsWith("todo")) {
				taskDetailsString = commandDetails.replaceFirst("todo", "").trim();
				if (taskDetailsString.length() == 0) {
					throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
				} else {
					return new AddTodoCommand(taskDetailsString);
				}
			} else {
				String[] taskDetails;
				if (commandDetails.startsWith("deadline")) {
					taskDetailsString = commandDetails.replaceFirst("deadline", "").trim();
					return new AddDeadlineCommand(taskDetailsString);
				} else if (commandDetails.startsWith("event")) {
					taskDetailsString = commandDetails.replaceFirst("event", "").trim();
					return new AddEventCommand(taskDetailsString);
				} else {
					throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
				}
			}
		}
	}

}
