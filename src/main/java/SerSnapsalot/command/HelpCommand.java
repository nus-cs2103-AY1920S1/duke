package SerSnapsalot.command;

import SerSnapsalot.ui.Ui;
import SerSnapsalot.taskList.TaskList;
import SerSnapsalot.storage.Storage;
/**
 * Represents the help command.
 * Displays the list of keywords that can be invoked.
 */
public class HelpCommand extends Command {

	public HelpCommand(String type, String command) {
		super(type, command);
	}

	/**
	 * Executes the command.HelpCommand.
	 *
	 * @param ui       The Ui currently running.
	 * @param taskList The TaskList Class containing the task list.
	 * @param storage  The Storage class containing the name of file the be read.
	 * @return output The message detailing the keywords that can be invoked.
	 */
	@Override
	public String execute(Ui ui, TaskList taskList, Storage storage) {
		String output = "";
		output += "Here's the keywords to activate the suit:\n";
		output += "  --Add a task: deadline/event/todo\n";
		output += "  --Display current task list: list\n";
		output += "  --Mark a task as done: done\n";
		output += "  --Delete a task: delete\n";
		output += "  --Search for a task by keyword: find\n";
		output += "  --Save the current task list to backup: save\n";
		output += "  --Archives the current task list : archive save\n";
		output += "  --Loads task list from the archive load\n";
		output += "  --Clear the current task list: clear\n";
		output += "  --Exit the program: bye\n";
		return output;
	}
}
