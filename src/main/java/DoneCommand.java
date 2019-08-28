/**
 * DoneCommand class which extends the abstract class Command.
 * This class handles the completion of tasks, and subsequent updating
 * the txt file.
 */
public class DoneCommand extends Command {
	protected String command;
	
	/**
	 * Class constructor
	 *
	 * @param command command to complete task
	 */
	public DoneCommand(String command) {
		this.command = command;
	}
	
	/**
	 * Method that overrides the abstract method in Command class.
	 * Completes the task requested by the user from the TaskList by marking them as done.
	 * Update the txt file by marking the respective task as done.
	 *
	 * @param tasks ArrayList of Tasks that keep tracks of the Tasks.
	 * @param storage Handles the reading and writing of the txt file.
	 */
	public void execute(TaskList tasks, Storage storage) {
		tasks.completeTask(command);
		storage.updateComplete(command);
	}
	
	/**
	 * DoneCommand does not exit program.
	 *
	 * @return False as this command does not end the program.
	 */
	public boolean isExit() {
		return false;
	}
}
