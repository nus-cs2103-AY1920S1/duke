import java.util.Scanner;

public class DeleteCommand extends Command {
	public DeleteCommand(Scanner inFullCommandScanner) {
		super(inFullCommandScanner);
	}

	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		if (!inFullCommandScanner.hasNext()) {
			throw new DukeException("no number provided");
		}
		Integer taskToDeleteIndex = Integer.parseInt(inFullCommandScanner.next());
		Task taskToBeDeleted;
		try {
			taskToBeDeleted = tasks.removeTask(taskToDeleteIndex - 1);
			StringBuilder output = new StringBuilder();
			output.append("Noted. I've removed this task:\n");
			output.append("    " + taskToBeDeleted + "\n");
			output.append(String.format("Now you have %d tasks in the list.", tasks.getTaskListSize()));
			ui.displayLine(output.toString());
		} catch (Exception e) {
			throw new DukeException(e.getMessage());
		}
	}
}
