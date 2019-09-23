import java.util.Scanner;

/**
 * Represents the parsing of user commands into actual command objects.
 * 		Basically a switch for deciding which commands to execute based on the input
 */
public class Parser {
	public Parser() { }

	/**
	 * parse takes in the user's input as a fullCommand and decideds which command object to initialize based on the
	 * 		first word of the fullCommand
	 * @param fullCommand the entire command the user has input into the duke program
	 * @return returns the corresponding command with the user's input
	 * @throws DukeException throws exception if the command is not found.
	 */
	public static Command parse(String fullCommand) throws DukeException {
		Scanner inFullCommandScanner = new Scanner(fullCommand);
		String command = inFullCommandScanner.next();
		if (command.equals("bye")) {
			return new ExitCommand();
		} else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
			return new AddCommand(command, inFullCommandScanner);
		} else if (command.equals("list")){
			return new ListCommand();
		} else if (command.equals("delete")) {
			return new DeleteCommand(inFullCommandScanner);
		} else if (command.equals("done")) {
			return new DoneCommand(inFullCommandScanner);
		} else if (command.equals("find")) {
			return new FindCommand(inFullCommandScanner);
		} else if (command.equals("sort")) {
			return new SortCommand(inFullCommandScanner);
		} else {
			throw new DukeException("command not found");
		}
	}
}
