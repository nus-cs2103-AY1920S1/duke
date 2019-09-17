package duke;

import duke.command.Command;

import duke.exception.LoadingErrorDukeException;

/**
 * Represents the Duke bot. Allows for easy and organised management of tasks.
 */

public class Duke {
	/**
	 * Represents exception handler of Duke.
	 */
	private ExceptionHandler exceptionHandler;
	/**
	 * Represents task list which stores all tasks given to Duke.
	 */
	private TaskList tasks;

	/**
	 * Represents storage function of Duke that helps to load and save data to disk.
	 */
	private static Storage storage;

	/**
	 * Constructor of Duke. Sets up user interface, storage and task list.
	 */
	public Duke() {
		exceptionHandler = new ExceptionHandler();
		storage = new Storage("src/data/list.txt");
		try {
			tasks = new TaskList(storage.loadList());
		} catch (LoadingErrorDukeException e) {
			tasks = new TaskList();
		}
	}

	/**
	 * Get Duke's response to user input.
	 *
	 * @param input String representation of user input.
	 * @return String representation of Duke's response to user input.
	 */
	public String getResponse(String input) {
		try {
			Command command = Parser.parse(input);
			return command.execute(tasks, storage);
		} catch (Exception e) {
			return exceptionHandler.showError(e);
		}
	}
}
