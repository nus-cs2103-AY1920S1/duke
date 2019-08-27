public class Parser {

	public static Command parse(String fullCommand) {
		if (fullCommand.equals("list")) {
			return new ListCommand(fullCommand);
		} else if (fullCommand.startsWith("done")) {
			return new DoneCommand(fullCommand);
		} else if (fullCommand.startsWith("delete")) {
			return new DeleteCommand(fullCommand);
		} else if (fullCommand.equals("bye")) {
			return new ByeCommand(fullCommand);
		}
		else {
			return new AddCommand(fullCommand);
		}
	}
}
