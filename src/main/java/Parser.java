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
		} else if (command.equals("find")) {
			return new FindCommand(command, remainingCommand);
		} else if (command.equals("update")) {
			return new UpdateCommand(command, remainingCommand);
		} else {
			return new AddCommand(command, remainingCommand);
		}
	}
}