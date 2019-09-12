import java.util.ArrayList;

class ListCommand extends Command {
	public ListCommand(String command, String remainingCommand) {
		super(command, remainingCommand);
	}

	public void execute(TaskList tasks, Ui ui) {
		ui.print("Here are the tasks in your list:\n");
		ArrayList<Task> list = tasks.getList();
		for (int i = 0; i < list.size(); i++) {
			ui.print((i + 1) + "." + list.get(i).toString() + "\n");
		}
	}
}