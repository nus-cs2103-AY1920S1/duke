import exceptions.DukeException;
import tasks.Task;
import utilities.Parser;
import utilities.Storage;
import utilities.TaskList;
import utilities.Ui;

/**
 * @author bakwxh
 * @version 0.1
 */
public class Duke {
	private Storage storage;
	private TaskList tasks;
	private Ui ui;
	private Parser parser;
	private boolean initialised;
	private boolean isBye;

    /**
     * Constructor.
     * @param filePath Path to save file.
     */
    public Duke(final String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        ui.setTaskList(tasks);
        parser = new Parser();
        initialised = false;
        isBye = false;
    }

	/**
	 * Main method of Duke. Takes in input from GUI, processes and
	 * does the appropriate reaction to the tasklist, and generates duke's
	 * response.
     * @param rawInput User's raw input from GUI.
	 * @return String if Duke's response.
     */
    public String run(String rawInput) {
		if (!initialised) {
			loadData();
		}

		String command = parser.getCommand(rawInput);
    	String result = "";
		try {
			result = logicAndResponse(command, rawInput);
		} catch (DukeException e) {
			result = ui.showException(e);
		}
		return result;
    }

	private String logicAndResponse(String command, String rawInput) throws DukeException {
    	String result = "";
		switch (command) {
			case "bye":
				result = ui.printBye();
				isBye = true;
				break;
			case "list":
				result = ui.printList();
				break;
			case "find":
				result = ui.printFind(parser.processFind(rawInput));
				break;
			case "done":
				int index = parser.processDone(rawInput);
				tasks.doneTask(index);
				storage.saveMemory(tasks);
				result = ui.printDone(index);
				break;
			case "delete":
				Task deletedTask = tasks.deleteTask(parser.processDelete(rawInput));
				storage.saveMemory(tasks);
				result = ui.printDeleted(deletedTask);
				break;
			case "todo":
			case "deadline":
			case "event":
				Task newTask = parser.generateTask(rawInput);
				tasks.addTask(newTask);
				storage.saveMemory(tasks);
				result = ui.printAdded();
				break;
			case "undo":
				result = ui.printUndo(tasks.undoTask());
				storage.saveMemory(tasks);
				break;
			case "none":
				result = ui.showLoadingError();
				break;
		}
		return result;
	}

	/**
	 * You should have your own function to generate a response to user input.
	 * Replace this stub with your completed method.
	 */
	String getResponse(String input) {
		if (!isBye) return run(input);
		else return "";
	}

	void loadData() {
		try {
			storage.load();
			initialised = true;
		} catch (DukeException e) {
			ui.showException(e);
		}
	}

	String getIntro() {
		return ui.logoAndIntro();
	}
}