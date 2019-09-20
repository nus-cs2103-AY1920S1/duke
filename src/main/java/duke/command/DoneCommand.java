package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents a done command.
 * To set task from task list as done, perform Ui task and save updated list to hard disk.
 */
public class DoneCommand extends Command {
	private int taskNumberDone;
	
	/**
	 * Constructor for creating a done command.
	 *
	 * @param command        String variable of the command input by user.
	 * @param pendingTask    Task to be added into the list.
	 * @param taskNumberDone Integer of the index of task to execute done.
	 */
	public DoneCommand(String command, Task pendingTask, int taskNumberDone) {
		super(command, pendingTask);
		this.taskNumberDone = taskNumberDone;
	}
	
	/**
	 * Set task from task list as done, perform Ui display and save to hard disk.
	 *
	 * @param list    List containing all tasks.
	 * @param ui      Ui interface of duke.
	 * @param storage Storage interface.
	 * @return String variable of message after command execution.
	 */
	@Override
	public String execute(TaskList list, Ui ui, Storage storage) throws IOException {
		Task t = list.setTaskDone(taskNumberDone - 1);
		//storage.save(list.printList());
		return ui.getDoneTask(t);
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
