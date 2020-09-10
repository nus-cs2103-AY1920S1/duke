package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.CommandNotFoundException;
import duke.parser.IncorrectArgumentsException;
import duke.parser.IncorrectFileFormatException;
import duke.parser.IncorrectNumberOfArgumentsException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.parser.Parser;
import duke.command.Command;

/**
 * Represents a Duke - interactive bot.
 * Contains functions to operate the bot.
 */
public class Duke {
	private Storage storage;
	private TaskList tasks;
	private Ui ui;
	
	/**
	 * Constructor to initialize Duke for startup.
	 * Set up storage, parser and ui.
	 */
	public Duke() {
		Parser.initialize();
		storage = new Storage();
		ui = new Ui();
	}
	
	/**
	 * Performs Duke start up operation to generate task list.
	 */
	public void performDukeStartup() throws IncorrectFileFormatException, FileNotFoundException,
											IncorrectArgumentsException {
		tasks = new TaskList();
		try {
			tasks = new TaskList(storage.load(ui));
		} catch (IncorrectFileFormatException j) {
			throw new IncorrectFileFormatException(ui.getLoadingError());
		} catch (NullPointerException n) {
			throw new NullPointerException(ui.getIndexError());
		} catch (FileNotFoundException f1) {
			throw new FileNotFoundException(ui.getLoadingError());
		} catch (IOException e) {
			// error
		} catch (IncorrectArgumentsException e) {
			throw e;
		}
	}
	
	/**
	 * Generate a GUI dialog box with duke response and image.
	 *
	 * @return Dialog box with duke response and image.
	 */
	public String getDukeResponse(String input) {
		String output;
		try {
			Command c = Parser.parse(input);
			output = c.execute(tasks, ui, storage);
		} catch (IndexOutOfBoundsException e) {
			output = "Incorrect input value.\nPlease check again.";
		} catch (CommandNotFoundException c) {
			output = "Command not found.\nPlease check again.";
		} catch (NullPointerException n) {
			output = "Internal error encountered. (Null Ptr)";
		} catch (IOException i) {
			output = "Internal error encountered." + " (IO exception)";
		} catch (IncorrectNumberOfArgumentsException a) {
			output = "Incorrect command format.\nPlease check again.";
		} catch (IncorrectArgumentsException e) {
			output = "Incorrect arguments supplied.\nPlease check again.\n";
		}
		return output;
	}
	
	/**
	 * Generate a GUI dialog box with welcome message.
	 *
	 * @return Dialog box with duke welcome message and image.
	 */
	public String getDukeWelcome() {
		return ui.getWelcome();
	}
}
