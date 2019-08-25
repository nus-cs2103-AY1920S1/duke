import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

	private Storage storage;
	private TaskList tasks;
	private Ui ui;

	public Duke(String filePath) {
		ui = new Ui();
		storage = new Storage(filePath);
		try {
			tasks = new TaskList(storage.load());
		} catch (DukeException exception) {
			ui.printLoadingErrorMessage(exception);
			tasks = new TaskList();
		}
	}

	public void run() {
		ui.printLogo();
		ui.greetUser();
		boolean isExit = false;
		while (!isExit) {
			try {
				String fullCommand = ui.readCommand();
				ui.printLine();
				Command c = Parser.parse(fullCommand);
				c.execute(tasks, ui, storage);
				isExit = c.isExit();
			} catch (DukeException exception) {
				ui.printExceptionMessage(exception);
			} finally {
				ui.printLine();
			}
		}

	}

	public static void main(String[] args) {
		new Duke("C:\\Users\\Yi Wai\\Documents\\Year 2 Semester 1\\CS2103\\duke\\data\\duke.txt").run();
	}

}
