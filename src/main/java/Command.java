/**
 *An abstract class which consist of the method for the execution of a command.
 */
public abstract class Command {

	/**
	 * Executes the command.
	 * @param tasks Tasklist consisting of current tasks.
	 * @param storage Updating of storage with new tasks.
	 */
	public abstract String execute(TaskList tasks , DukeWriteFile storage);


}
