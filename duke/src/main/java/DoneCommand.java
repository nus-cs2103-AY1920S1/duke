import java.util.ArrayList;

class DoneCommand extends Command {
	public DoneCommand(String command, String remainingCommand) {
		super(command, remainingCommand);
	}

	public void execute(TaskList tasks, Ui ui) throws DukeException {
		ArrayList<Task> list = tasks.getList();
		if (list.isEmpty()) {
			throw new DukeException("You have no task to do!");
		} else if (remainingCommand.isEmpty()) {
			throw new DukeException("Do include the task number that you have done!");
		}
		
		int index = Integer.valueOf(remainingCommand); // assuming only has number left
		Task task = list.get(index - 1);
		task.markAsDone();
		ui.print("Nice! I've marked this task as done:\n  " + task.toString());
	}
}