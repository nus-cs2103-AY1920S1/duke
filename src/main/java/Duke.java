/**
 * Represents the main class of Duke program.
 */

public class Duke {
	private static final String taskListArchivalPath = "data/duke.txt";
	private Storage storage;
	private TaskList tasks;
	private Ui ui;

	/**
	 * Constructor of Duke class. Initializes Storage, Ui and TaskList.
	 * @param filePath the string representation of the path of the archival file. The file
	 * 	 *  *                 this is used to load previous tasks. When the program is terminated
	 * 	 *  *                 tasks are also updated into this file.
	 */
	public Duke(String filePath) {
		ui = new Ui();
		storage = new Storage(filePath);
		try {
			tasks = new TaskList(storage.load());
		} catch (DukeException ex) {
			ui.showLoadingErrors(ex);
			tasks = new TaskList();
		}
	}

	/**
	 * Starts the Duke program.
	 */
	public void run() {
		ui.showWelcome();
		boolean isExit = false;
		while (!isExit) {
			try {
				String fullCommand = ui.readCommand();
				ui.showBreakLine();
				Command c = Parser.parse(fullCommand);
				c.execute(tasks, ui, storage);
				isExit = c.isExit();
			} catch (DukeException e) {
				ui.showError(e.getMessage());
			} finally {
				ui.showBreakLine();
			}
		}
	}

	/**
	 * This is the main method of the program simply calls the run method of Duke
	 * @param args
	 */
	public static void main(String[] args) {
		new Duke(taskListArchivalPath).run();
	}
}

