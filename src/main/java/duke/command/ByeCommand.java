package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Performs a Bye command
 * by executing the Ui to show bye screen.
 */
public class ByeCommand extends Command {
	
	/**
	 * Constructor to create a Bye command.
	 *
	 * @param command     String variable of the command.
	 * @param pendingTask Task to add into the list.
	 */
	public ByeCommand(String command, Task pendingTask) {
		super(command, pendingTask);
	}
	
	/**
	 * Shows Ui screen of bye.
	 *
	 * @param list    List containing all tasks.
	 * @param ui      Ui interface of duke.
	 * @param storage Storage interface.
	 * @return String variable of message after command execution.
	 */
	@Override
	public String execute(TaskList list, Ui ui, Storage storage) {
		return ui.getBye();
	}
	
	/**
	 * Return boolean indicating if command is exit command.
	 *
	 * @return boolean flag indicating if is exit command.
	 */
	@Override
	public boolean isExit() {
		return true;
	}
}
