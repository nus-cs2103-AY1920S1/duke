package duke;

import duke.command.Command;
import java.lang.String;

/**
 * Encapsulates a chat bot.
 */
public class Duke {
    protected static final String DUKE_FILE = "duke.txt";

    protected Parser parser;
    protected Storage storage;
    protected TaskList taskList;
    protected Ui ui;

    /**
     * Constructs a Duke object.
     *
     * @param filePath  Path to saved data file on hard disk.
     */
    public Duke(String filePath) {
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    /**
     * Initiates a full run of the chat bot.
     */
    public void run() {
        start();
        runUntilByeCommand();
        exit();
    }

    /**
     * Starts the chat bot.
     */
    public void start() {
        ui.showWelcomeMessage();
    }

    /**
     * Runs the chat bot.
     */
    public void runUntilByeCommand() {
        boolean isBye = false;
        while (!isBye) {
            try {
                String input = ui.input();
                Command command = parser.parse(input);
                command.execute(storage, taskList, ui);
                isBye = command.isBye();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Exits the chat bot.
     */
    public void exit() {
        ui.showByeMessage();
        System.exit(0);
    }

    /**
     * Main method. Initiates a full run of a chat bot tied to a specific saved data file on the hard disk.
     * @param args The command line arguments.
     **/
    public static void main(String[] args) {
        new Duke(DUKE_FILE).run();
    }
}

