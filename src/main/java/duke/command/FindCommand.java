package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
	private String keyword;
	
	/**
	 * Constructor to create a find command.
	 *
	 * @param command     String variable of the command.
	 * @param keyword     String containing word to search for in tasks.
	 * @param pendingTask Task to add into the list.
	 */
	public FindCommand(String command, String keyword, Task pendingTask) {
		super(command, pendingTask);
		this.keyword = keyword;
	}
	
	/**
	 * Performs the execution of the find command.
	 *
	 * @param list    List of tasks in application stored.
	 * @param ui      UI interface to show messages to the user.
	 * @param storage Storage interface to save task list to disk.
	 * @return String variable of message after command execution.
	 */
	@Override
	public String execute(TaskList list, Ui ui, Storage storage) {
		ArrayList<String> listFound = list.find(keyword);
		return ui.getMatchingTaskList(listFound);
	}
	
	/**
	 * Return boolean indicating if command is exit command.
	 *
	 * @return boolean flag indicating if is exit command.
	 */
	@Override
	public boolean isExit() {
		return false;
	}
}
