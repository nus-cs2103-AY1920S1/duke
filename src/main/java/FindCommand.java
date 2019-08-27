import java.util.ArrayList;
import java.util.Scanner;

public class FindCommand extends Command {
	public FindCommand(Scanner inFullCommandScanner) {
		super(inFullCommandScanner);
	}

	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		String searchKeyword = inFullCommandScanner.nextLine();
		TaskList tasksFound = tasks.findByKeyword(searchKeyword);
		if (tasksFound == null) {
			ui.displayLine("apologies there were no tasks found with the keyword: " + searchKeyword);
		} else {
			StringBuilder output = new StringBuilder();
			output.append("Here are the matching tasks in your list:\n");
			output.append(tasksFound);
			ui.displayLine(output.toString());
		}
	}
}
