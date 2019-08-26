package weomucat.duke;

import weomucat.duke.command.listener.ByeCommandListener;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.StorageException;
import weomucat.duke.storage.TaskListStorage;
import weomucat.duke.task.TaskList;
import weomucat.duke.ui.Ui;

public class Duke implements ByeCommandListener {
	private static final String LOGO = " ____        _        \n"
			+ "	|  _ \\ _   _| | _____ \n"
			+ "	| | | | | | | |/ / _ \\\n"
			+ "	| |_| | |_| |   <  __/\n"
			+ "	|____/ \\__,_|_|\\_\\___|\n";

	public static final String DATETIME_PARSE_PATTERN = "dd/MM/yy HHmm";
	public static final String DATETIME_FORMAT_PATTERN = "dd MMMM yyyy, hh:mma, O";

	private boolean running;
	private TaskList taskList;
	private TaskListStorage storage;
	private Ui ui;

	public static void main(String[] args) {
		new Duke("data/tasks").run();
	}

	public Duke(String taskListPath) {
		/* Initialize */

		Controller controller = new Controller();
		this.taskList = new TaskList();
		this.storage = new TaskListStorage(taskListPath);

		// Read from user input from stdin.
		this.ui = new Ui(System.in);

		// Set up event listeners
		this.ui.newUserInputListener(controller);

		controller.newAddTaskCommandListener(this.taskList);
		controller.newDeleteTaskCommandListener(this.taskList);
		controller.newDoneTaskCommandListener(this.taskList);
		controller.newListTaskCommandListener(this.taskList);
		controller.newByeCommandListener(this);

		taskList.newAddTaskListener(this.storage);
		taskList.newDeleteTaskListener(this.storage);
		taskList.newDoneTaskListener(this.storage);

		taskList.newAddTaskListener(this.ui);
		taskList.newDeleteTaskListener(this.ui);
		taskList.newDoneTaskListener(this.ui);
		taskList.newListTaskListener(this.ui);
	}

	private void run() {
		// Try to initialize TaskList from disk if database exists.
		if (this.storage.exists()) {
			try {
				this.taskList = new TaskList(this.storage.load());
				this.ui.displayMessage("Loaded tasks from disk.");
			} catch (StorageException e) {
				this.ui.displayError(e.getMessage());
			}
		}

		// Greet user
		this.ui.displayMessage(LOGO, "Hello! I'm Duke", "What can I do for you?");

		this.running = true;
		while (this.running) {
			try {
				// Handle next user input
				this.ui.nextUserInput();
			} catch (DukeException e) {
				this.ui.displayError(e.getMessage());
			}
		}

		// Farewell user
		this.ui.displayMessage("Bye. Hope to see you again soon!");
	}

	@Override
	public void byeCommandUpdate() {
		this.running = false;
	}
}
