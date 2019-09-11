import java.io.IOException;

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
