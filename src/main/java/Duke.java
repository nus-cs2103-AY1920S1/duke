public class Duke {
	private static final String taskListArchivalPath = "data/duke.txt";

	private Storage storage;
	private TaskList tasks;
	private Ui ui;

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

	public static void main(String[] args) {
		new Duke(taskListArchivalPath).run();
	}
}

