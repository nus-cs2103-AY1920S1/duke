package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a list command.
 * To obtain list of task to print, perform Ui task.
 */
public class ListCommand extends Command {
	
	/**
	 * Constructor to create a list command.
	 *
	 * @param command String variable of the command.
	 */
	public ListCommand(String command) {
		super(command, new Task());
	}
	
	/**
	 * Obtain list of tasks to print, perform Ui display and save to hard disk.
	 *
	 * @param list    List containing all tasks.
	 * @param ui      Ui interface of duke.
	 * @param storage Storage interface.
	 * @return String variable of message after command execution.
	 */
	@Override
	public String execute(TaskList list, Ui ui, Storage storage) {
		ArrayList<String> listToPrint = list.printList();
		return ui.getList(listToPrint);
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
