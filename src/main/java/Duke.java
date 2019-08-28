import java.util.Scanner;

/**
 * @author bakwxh
 * @version 0.1
 */
public class Duke {
	/**
	 * Storage object.
	 */
	private Storage storage;
	/**
	 * Task list object.
	 */
	private TaskList tasks;
	/**
	 * UI object.
	 */
	private Ui ui;
	/**
	 * Parser object.
	 */
	private Parser parser;

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
    }

    /**
     * Main run method.
     */
    public void run() {
    	try {
			storage.load();
		} catch (DukeException e) {
			ui.showException(e);
		}
    	ui.logoAndIntro();
    	Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String rawInput = sc.nextLine();
			String command = parser.getCommand(rawInput);
			try {
				if (command.equals("bye")) {
					ui.printBye();
					break;
				} else if (command.equals("list")) {
					ui.printList();
				} else if (command.equals("find")) {
					ui.printFind(parser.processFind(rawInput));
				} else if (command.equals("done")) {
					int index = parser.processDone(rawInput);
					tasks.doneTask(index);
					ui.printDone(index);
				} else if (command.equals("delete")) {
					ui.printDeleted(tasks.deleteTask(parser.processDelete(rawInput)));
	    		} else if (command.equals("todo")) {
					tasks.addTodo(parser.todoDesc(rawInput));
					ui.printAdded();
				} else if (command.equals("deadline")) {
					tasks.addDeadline(parser.deadlineDesc(rawInput), parser.deadlineTime(rawInput));
					ui.printAdded();
				} else if (command.equals("event")) {
					tasks.addEvent(parser.eventDesc(rawInput), parser.eventTime(rawInput));
					ui.printAdded();
				} else {
					ui.showLoadingError();
				}
				storage.saveMemory(tasks);
	    	} catch (DukeException e) {
	    		ui.showException(e);
	    	}
		}
		sc.close();
    }

	/**
	 * Main method.
	 * @param args Args.
	 */
	public static void main(final String[] args) {
		String dir = System.getProperty("user.dir") + "/savedData.txt";
		new Duke(dir).run();
	}
}