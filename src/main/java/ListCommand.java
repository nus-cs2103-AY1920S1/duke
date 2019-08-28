import java.util.ArrayList;

/**
 * ListCommand class which extends the abstract class Command.
 * This class handles the printing of all tasks in the TaskList.
 */
public class ListCommand extends Command {
	protected String command;
	
	/**
	 * Class constructor
	 *
	 * @param command command to show all the tasks in TaskList
	 */
	public ListCommand(String command) {
		this.command = command;
	}
	
	/**
	 * Method that overrides the abstract method in Command class.
	 * Prints all the task from the TaskList.
	 *
	 * @param tasks ArrayList of Tasks that keep tracks of the Tasks.
	 * @param storage Handles the reading and writing of the txt file.
	 */
	public void execute(TaskList tasks, Storage storage) {
		ArrayList<Task> tasksList = tasks.getTasks();
		for (int i = 0; i < tasksList.size(); i++) {
			System.out.println(tasksList.get(i));
		}
	}
	
	/**
	 * ListCommand does not exit program.
	 *
	 * @return False as this command does not end the program.
	 */
	public boolean isExit() {
		return false;
	}
}
