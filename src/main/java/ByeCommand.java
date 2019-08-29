package duke.command;

import duke.component.Storage;
import duke.component.TaskList;

/**
 * ByeCommand class which extends the abstract class Command.
 * This class handles the exit of the program.
 */
public class ByeCommand extends Command {
	protected String command;
	
	/**
	 * Class constructor
	 *
	 * @param command command to exit the program.
	 */
	public ByeCommand(String command) {
		this.command = command;
	}
	
	/**
	 * Method that overrides the abstract method in Command class.
	 * Prints the goodbye message to the user.
	 *
	 * @param tasks ArrayList of Tasks that keep tracks of the Tasks.
	 * @param storage Handles the reading and writing of the txt file.
	 */
	public void execute(TaskList tasks, Storage storage) {
		System.out.println("BYE! See you again next time!");
	}
	
	/**
	 * ByeCommand exits the program.
	 *
	 * @return True as this command end the program.
	 */
	public boolean isExit() {
		return true;
	}
}
