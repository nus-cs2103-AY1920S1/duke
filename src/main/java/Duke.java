package duke;

import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Ui;
import duke.util.Storage;
import duke.task.TaskList;
import duke.command.Command;

/**
 * Application class for Duke.
 */
public class Duke {
    private static final String SAVE_PATH = "data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor of Duke class without parameters.
     */
    public Duke() {
        this(SAVE_PATH);
    }

    /**
     * Constructor of Duke class.
     * 
     * @param filePath Path to text file where save data is stored.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = this.storage.load();
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Main method for Duke.
     * 
     * @param args Arguments entered when main method is executed.
     */
    public static void main(String[] args) {
        new Duke(SAVE_PATH).run();
    }

    /**
     * Returns String as response to user input.
     * 
     * @param input Input entered by user.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            this.storage.save(tasks);
            if (c.isExit()) {
                System.exit(0);
            }
        } catch (DukeException e) {
            this.ui.showError(e.getMessage());
        }
        return this.ui.getOutput();
    }

    /**
     * Run method of Duke.
     */
    public void run() {
        this.ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                Command c = Parser.parse(ui.readCommand());
                c.execute(tasks, ui, storage);
                storage.save(tasks);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
