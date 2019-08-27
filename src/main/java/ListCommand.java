import java.util.Scanner;

public class ListCommand extends Command {
	public ListCommand() {
		super();
	}

	public void execute(TaskList tasks, Ui ui, Storage storage) {
		ui.displayLine(tasks.toString());
	}
}
