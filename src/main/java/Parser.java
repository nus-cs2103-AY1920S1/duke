import java.util.Scanner;

public class Parser {
	public Parser() { }

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
		} else {
			throw new DukeException("command not found");
		}
	}
}
