import duke.command.Command;

import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.FileNotFoundException;

/**
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various tasks.
 */
public class Duke {
    /** User Interface of Duke that handles input and output to and from the command line. */
    private Ui ui;
    /** Storage where the Tasks are retrieved from and stored to. */
    private Storage storage;
    /** List of Tasks Duke is currently tracking. */
    private TaskList tasks;

    /**
     * Constructor for Duke that instantiates the Ui and Storage classes.
     *
     * @param filePath Path to .txt file for Storage of tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Runs and starts the Duke chatbot program.
     */
    public void run() {
        try {
            tasks = storage.getTasks();
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.printError(e);
            tasks = new TaskList();
        }

        ui.printGreeting();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                Command c = Parser.parse(input);
                isExit = c.isExit();
                c.execute(tasks, ui, storage);
            } catch (DukeException e) {
                ui.printError(e);
            }
        }

        ui.printExit();
    }
}
