package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.CommandParser;
import duke.task.TaskList;
import duke.task.VersionedTaskList;
import duke.ui.Ui;

import static duke.ui.Message.MESSAGE_LOADING_ERROR;
import static duke.ui.Message.MESSAGE_WELCOME;
import static duke.ui.Message.formatErrorMsg;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    public Duke() {
        this(Storage.DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * Constructs a Duke object.
     *
     * @param filePath file path where the data is (to be) stored
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.isExit = false;
        try {
            this.tasks = new VersionedTaskList(storage.load());
        } catch (DukeException e) {
            ui.printResponse(MESSAGE_LOADING_ERROR);
            this.tasks = new VersionedTaskList();
        }
    }

    /**
     * Generates a response to user input.
     */
    public String getResponse(String input) {
        try {
            Command c = CommandParser.parse(input);
            this.isExit = c.isExit();
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return formatErrorMsg(e.getMessage());
        }
    }

    public boolean isExit() {
        return isExit;
    }

    private void run() {
        ui.printResponse(MESSAGE_WELCOME);
        ui.showLine();
        while (!this.isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = CommandParser.parse(fullCommand);
                ui.printResponse(c.execute(tasks, ui, storage));
                this.isExit = c.isExit();
            } catch (DukeException e) {
                ui.printResponse(formatErrorMsg(e.getMessage()));
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main entry point of the application.
     */
    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
