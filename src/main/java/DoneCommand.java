import java.util.Scanner;

public class DoneCommand extends Command {
	public DoneCommand(Scanner inFullCommandScanner) {
		super(inFullCommandScanner);
	}

	public void execute(TaskList tasks, Ui ui, Storage storage) {
		//read which task to complete and set as complete
		Integer taskNumber = Integer.parseInt(inFullCommandScanner.nextLine().trim()) - 1;
		Task task = tasks.markCompleted(taskNumber);
		StringBuilder sb = new StringBuilder("Nice! I've marked this task as done:\n");
		sb.append("    ");
		sb.append(task.toString());
		ui.displayLine(sb.toString());
	}
}
