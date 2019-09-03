package duke;

import duke.command.Command;
import duke.exception.DukeException;

import java.io.IOException;

/**
 * This class is the main class of the program. It helps users to help track their Duke.tasks
 * by creating a task list for them when users key in the respective input. The task list
 * is then automatically saved into a txt file which allows the users to view and keep track.
 */
public class Duke {
	private Storage storage;
	private TaskList tasks;
	private Ui ui;
	
	/**
	 * Class constructor
	 * Loads the stored txt file of Duke.tasks into the program.
	 *
	 * @param filePath filepath of the txt file stored
	 */
	public Duke(String filePath) {
		ui = new Ui();
		try {
			storage = new Storage(filePath);
			tasks = new TaskList(storage.load());
		} catch (DukeException e) {
			ui.showError(e);
		} catch (IOException e) {
			System.out.println("File not found");
		}
	}
	
	public static void main(String[] args) throws IOException {
		new Duke("/users/junhup/desktop/duke/src/duke.txt").run();
	}
	
	/**
	 * Main method of the entire program. Runs the program.
	 */
	public void run() {
		ui.showWelcome();
		boolean isExit = false;
		while (!isExit) {
			try {
				String fullCommand = ui.readCommand();
				Command c = Parser.parse(fullCommand);
				c.execute(tasks, storage);
				isExit = c.isExit();
			} catch (DukeException | IOException e) {
				ui.showError(e);
			}
		}
	}
}


