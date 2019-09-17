package command;

import ui.Ui;
import taskList.TaskList;
import storage.Storage;
/**
 * Represents the Exception command.
 * Returns the exception as a string to be displayed in DialogBox
 */
public class ExceptionCommand extends Command {

	public ExceptionCommand(String type, String command) {
		super(type, command);
	}

	/**
	 * Executes the ExceptionCommand.
	 *
	 * @param ui       The Ui currently running.
	 * @param taskList The TaskList Class containing the task list.
	 * @param storage  The Storage class containing the name of file the be read.
	 * @return output The String output for GUI message.
	 */
	@Override
	public String execute(Ui ui, TaskList taskList, Storage storage) {
		String output = this.command;
		return output;
	}
}
