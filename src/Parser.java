package main.java.components;

import main.java.command.AddCommand;
import main.java.command.ByeCommand;
import main.java.command.Command;
import main.java.command.DeleteCommand;
import main.java.command.DoneCommand;
import main.java.command.ListCommand;
import main.java.command.FindCommand;

/**
 * Parser class deals with making sense of the user command.
 */
public class Parser {
	
	/**
	 * A static method that parse the input command given by the user and
	 * deals with the command respectively.
	 *
	 * @param fullCommand makes sense of the input given by the user.
	 * @return a Command which will then be executed.
	 */
	public static Command parse(String fullCommand) {
		if (fullCommand.equals("list")) {
			return new ListCommand(fullCommand);
		} else if (fullCommand.startsWith("done")) {
			return new DoneCommand(fullCommand);
		} else if (fullCommand.startsWith("delete")) {
			return new DeleteCommand(fullCommand);
		} else if (fullCommand.startsWith("find")) {
			return new FindCommand(fullCommand);
		} else if (fullCommand.equals("bye")) {
			return new ByeCommand(fullCommand);
		} else {
			return new AddCommand(fullCommand);
		}
	}
}
