import java.io.IOException;

/**
 * Marks a task as done to indicate the completion of the task.
 */
public class DoneCommand extends Command {

	private String input;
	private String[] taskToBeCompleted;

	public DoneCommand(String input) throws DukeException {
		this.input = input;
		taskToBeCompleted = input.split(" ");
		if (taskToBeCompleted.length == 1) {
			throw new DukeException(Ui.WRONG_OP);
		}
		if (taskToBeCompleted.length > 2) {
			throw new DukeInvalidArgumentException(Ui.DONE_FORMAT, input);
		}
	}

	/**
	 * Executes the done command to mark the selected task as completed.
	 * @param tasks Tasklist consisting of current tasks.
	 * @param storage Updating of storage with new tasks.
	 * @return A string to indicate the completion of the selected task.
	 */
	public String execute(TaskList tasks, DukeWriteFile storage) {
		StringBuilder taskDone = new StringBuilder();
		try {

			int taskIndex = Integer.parseInt(taskToBeCompleted[1]);
			tasks.getTask(taskIndex - 1).markAsDone();
			taskDone.append(Ui.MARK_DONE);
			taskDone.append(tasks.getTask(taskIndex - 1).toString() + "\n" + Ui.BORDER);
			DukeWriteFile.writeToFile(DukeWriteFile.writeFile(tasks.getTaskList()));
		} catch (IOException e) {
			taskDone.append(Ui.BORDER + "\n" + e + "\n" + Ui.BORDER);
		} catch (IndexOutOfBoundsException e) {
			taskDone.append(Ui.BORDER + "\nTask number not found! Try again!\n" + Ui.BORDER);

		} catch (NumberFormatException e) {
			taskDone.append(Ui.BORDER + "\nOOPS!! Please input a task number!\n" + Ui.BORDER);

		}
		return taskDone.toString();
	}
}
