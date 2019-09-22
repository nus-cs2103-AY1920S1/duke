import java.util.ArrayList;

class DeleteCommand extends Command {
	public DeleteCommand(String command, String remainingCommand) {
		super(command, remainingCommand);
	}

	public void execute(TaskList tasks, Ui ui) throws DukeException {
		ArrayList<Task> list = tasks.getList();
		if (list.isEmpty()) {
			throw new DukeException("You have no task to delete!");
		} else if (remainingCommand.isEmpty()) {
			throw new DukeException("Which task do you want to delete?");
		}
		
		int index = Integer.valueOf(remainingCommand); // assuming only has number left
		Task task = list.get(index - 1);
		list.remove(task);
		ui.print("Noted. I've removed this task:\n  " + task.toString());
		ui.print("Now you have " + list.size() + " tasks in the list.");
	}
}