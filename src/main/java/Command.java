import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Represents the abstract parent class of all types of commands a user can input into the duke program
 */
abstract public class Command {
	protected boolean isExit;
	protected Scanner inFullCommandScanner;
	protected static final DateTimeFormatter dateTimeFormatter
		= DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

	/**
	 * Default constructor for command object
	 */
	public Command() {}

	/**
	 * Overloaded constructor for command object for commands which require further details from user
	 * @param inFullCommandScanner Scanner object to iterate through the rest of the details the user has input into
	 *                             duke.
	 */
	public Command(Scanner inFullCommandScanner) {
		this.inFullCommandScanner = inFullCommandScanner;
	}

	/**
	 * isExit represents if the duke program has been instructed by the user to shut down.
	 * @return returns the isExit attribute of the command object, only the Exit command alters this attribute
	 */
	public boolean isExit() {
		return this.isExit;
	}

	/**
	 * the core method for all commands, effects the needed changes in TaskList, Ui and Storage.
	 * @param tasks represents all the tasks added in memory
	 * @param ui represents the interaction between duke and the user
	 * @param storage represents the reading and writing to the archival file
	 * @throws DukeException
	 */
	abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
