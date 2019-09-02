import java.util.Scanner;

class Parser {
	/**
	 * Determines type of Command
	 * @param command as a String
	 * @return Command
	 */
	public static Command parse(String command, String remainingCommand) {
		if (command.equals("list")) {
			return new ListCommand(command, remainingCommand);
		} else if (command.equals("done")) {
			return new DoneCommand(command, remainingCommand);
		} else if (command.equals("delete")) {
			return new DeleteCommand(command, remainingCommand);
		} else if (command.equals("bye")) {
			return new ExitCommand(command, remainingCommand);
		} else {
			return new AddCommand(command, remainingCommand);
		}
	} 

	/*
	private Consumer<String> parse = (line) -> {
		String[] parts = line.split(" ");
		boolean isDone;
		String type = parts[0];
		int binary = Integer.valueOf(parts[1]);
		if (binary == 1) {
			isDone = true;
		} else {
			isDone = false;
		}
		String description = parts[2];
		loadLine(type, isDone, description);
	};

	private void loadLine(String type, boolean isDone, String description) throws DukeException {
		switch(type) {
		case "todo":
		    loadTodo(isDone, description);
		    break;

		case "deadline":
		    loadDeadline(isDone, description);
		    break;

		case "event":
		    loadEvent(isDone, description);
		    break;
		}
	}
	*/
}