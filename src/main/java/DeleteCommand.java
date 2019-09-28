import java.io.IOException;

/**
 * Deletes a task inside the tasklist which has been done or deemed unnecessary.
 */
public class DeleteCommand extends Command {

	private String input;
	private String[] taskDelete;

	public DeleteCommand(String input) throws DukeException {
		this.input = input;
		taskDelete = input.split(" ");
		if (taskDelete.length == 1) {
			throw new DukeException("OOPS!!! Which task would you like to delete?");
		}
		if (taskDelete.length > 2) {
			throw new DukeInvalidArgumentException(Ui.DELETE_FORMAT, input);
		}
	}

	/**
	 * Executes the delete command, removing the task in the tasklist.
	 *
	 * @param tasks   Tasklist consisting of current tasks.
	 * @param storage Updating of storage with new tasks.
	 * @return String to display the task that has been deleted.
	 */
	public String execute(TaskList tasks, DukeWriteFile storage) {
		StringBuilder taskToBeDeleted = new StringBuilder();
		try {
			int taskIndex = Integer.parseInt(taskDelete[1]);
			String removedTask = tasks.getTask(taskIndex - 1).toString();
			tasks.deleteTask(taskIndex - 1);
			taskToBeDeleted.append(Ui.BORDER + "\nNoted. I've removed this task:\n");
			taskToBeDeleted.append(removedTask + "\n");
			int numberOfTask = tasks.getCounter();
			taskToBeDeleted.append("Now you have " + numberOfTask + " tasks in the list.\n" + Ui.BORDER);
			storage.writeToFile(DukeWriteFile.writeFile(tasks.getTaskList()));
		} catch (NumberFormatException e) {
			taskToBeDeleted.append(Ui.BORDER + "\nOOPS!! Please input a task number!\n" + Ui.BORDER);

		} catch (IndexOutOfBoundsException e) {
			taskToBeDeleted.append(Ui.BORDER + "\nTask number not found! Try again!\n" + Ui.BORDER);

		} catch (IOException e) {
			taskToBeDeleted.append(Ui.BORDER + "\n" + e + "\n" + Ui.BORDER);

		}
		return taskToBeDeleted.toString();
	}
}
