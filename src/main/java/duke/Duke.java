package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeShutDownException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

/**
 * CLI Chat assistant that keep tracks of tasks.
 * Will be developed incrementally over the course
 * of CS2103.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Drives the main code to create a Duke object and run it. It is the CLI entry
     * point.
     *
     * @param args command line parameters for Duke. First one is the saved
     *             filepath.
     */
    public static void main(String[] args) {
        Duke duke;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        if (args.length > 1) {
            duke = new Duke(args[1]);
        } else {
            duke = new Duke();
        }
        duke.start();
    }

    /**
     * Returns a Duke object, which can be used
     * to start the chat assistant driver loop.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();

        try {
            this.taskList = new TaskList(this.storage.loadFromDisk());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    /**
     * Returns a Duke object with a specified load file path.
     * Its internal task list will be loaded from the filepath
     * specified by the first command line argument.
     *
     * @param filePath path to saved file.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.taskList = new TaskList(this.storage.loadFromDisk());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    /**
     * Starts the main loop for the chat assistant to take in
     * inputs and process them.
     */
    public void start() {

        this.ui.greetHello(); // greet user on startup

        do { // main loop and exception handler
            try {
                String input = ui.getUserCommand();
                this.ui.showLine();
                Command c = Parser.parseForCommands(input); // send it off to be parsed
                c.initialize(this.storage, this.taskList, this.ui);
                c.execute();
            } catch (DukeShutDownException e) {
                ui.greetGoodbye();
                break;
            } catch (DukeException e) {
                ui.displayMessage(e.getMessage());
            } finally {
                this.ui.showLine();
            }
        } while (true);
    }
}