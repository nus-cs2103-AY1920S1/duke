package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.exception.InvalidInputException;

/**
 * AddCommand class which extends the abstract class Command.
 * This class handles any addition of tasks into the TaskList class and
 * subsequently updating the txt file.
 */
public class AddCommand extends Command {
	protected String command;
	
	/**
	 * Class constructor
	 *
	 * @param command command to add task
	 */
	public AddCommand(String command) {
		this.command = command;
	}
	
	/**
	 * Method that overrides the abstract method in Command class.
	 * Adds the tasks requested by the user into TaskList.
	 * Update the txt file by adding the respective task.
	 *
	 * @param tasks ArrayList of Tasks that keep tracks of the Tasks.
	 * @param storage Handles the reading and writing of the txt file.
	 */
	public void execute(TaskList tasks, Storage storage){
		try {
			if (command.startsWith("todo")) {
				System.out.println("in");
				tasks.addTodo(command);
				storage.storeTodo(command);
			} else if (command.startsWith("deadline")) {
				tasks.addDeadline(command);
				storage.storeDeadline(command);
			} else if (command.startsWith("event")) {
				tasks.addEvent(command);
				storage.storeEvent(command);
			} else {
				throw new InvalidInputException();
			}
		} catch (Exception e) {
			handleException(e);
		}
	}
	
	/**
	 * AddCommand does not exit program.
	 *
	 * @return False as this command does not end the program.
	 */
	public boolean isExit() {
		return false;
	}
}
