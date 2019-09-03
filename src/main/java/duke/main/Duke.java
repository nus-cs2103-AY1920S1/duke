package duke.main;

import duke.command.Command;
import duke.component.Parser;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.exception.DukeException;

/**
 * Represents chatbot Duke with main method.
 * The 'Duke' class supports operators (i) allows user interaction with chatbot.
 */
public class Duke {

    /**
     * Store and load tasks from text in text file.
     */
    private Storage storage;

    /**
     * Task List of tasks.
     */
    private TaskList tasks;

    /**
     * User Interface that takes care of user interaction with chatbot.
     */
    private Ui ui;

    /**
     * Creates a new instance of Duke with file path of text file.
     *
     * @param filePath File path of text file that stores tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    /**
     * Interacts with user by reading user input
     * and print out response.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Creates a new instance of Duke and runs it
     * to allow user interaction with chatbot.
     * @param args Running the duke chatbot
     */
    public static void main(String[] args) {
        new Duke("/Users/joannayap/Desktop/duke/data/duke.txt").run();
    }
}
