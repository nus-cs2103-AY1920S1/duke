import java.util.ArrayList;

public class ListCommand extends Command {

	public ListCommand() {
		super();
	}

	public void execute(TaskList tasks, Ui ui, Storage storage) {
		ui.printMessage("\t Here are the tasks in your list:");
		for (int i = 1; i <= tasks.getSize(); i++) {
			ui.printMessage("\t " + i + ". " + tasks.getTask(i - 1));
		}
	}

	public boolean isExit() {
		return false;
	}

}
