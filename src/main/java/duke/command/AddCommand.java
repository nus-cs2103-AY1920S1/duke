package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents an add command.
 * To add task to task list, perform Ui task and save to hard disk.
 */
public class AddCommand extends Command {
	
	/**
	 * Constructor to create an Add command.
	 *
	 * @param command     String variable of the command.
	 * @param pendingTask Task to add into the list.
	 */
	public AddCommand(String command, Task pendingTask) {
		super(command, pendingTask);
	}
	
	/**
	 * Added new task to task list, perform Ui display and save to hard disk.
	 *
	 * @param list    List containing all tasks.
	 * @param ui      Ui interface of duke.
	 * @param storage Storage interface.
	 * @return String variable of message after command execution.
	 */
	@Override
	public String execute(TaskList list, Ui ui, Storage storage) throws IOException {
		Task temp = getPendingTask();
		list.addNewTask(temp);
		list.increaseTaskCount();
		storage.save(list.printList());
		
		String addedTaskMessage = ui.getAddedTaskMessage(temp.getFirstCharTask(), temp.getIsDone(),
														 temp.getTaskDescription(),
														 list.getTaskCount());
		return addedTaskMessage;
	}
	
	/**
	 * Return boolean indicating if command is exit command.
	 *
	 * @return boolean flag indicating if is exit command.
	 */
	public boolean isExit() {
		return false;
	}
}
