package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.CommandParser;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Message.MESSAGE_LOADING_ERROR;
import static duke.ui.Message.MESSAGE_WELCOME;
import static duke.ui.Message.formatErrorMsg;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        this(Storage.DEFAULT_STORAGE_FILEPATH);
    }

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printResponse(MESSAGE_LOADING_ERROR);
            this.tasks = new TaskList();
        }
    }

    /**
     * Generate a response to user input.
     */
    public String getResponse(String input) {
        // TODO: Terminate the program when ExitCommand is triggered
        try {
            String fullCommand = input;
            Command c = CommandParser.parse(fullCommand);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return formatErrorMsg(e.getMessage());
        }
    }

    /**
     * Main entry point of the application.
     */
    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }

    private void run() {
        ui.printResponse(MESSAGE_WELCOME);
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = CommandParser.parse(fullCommand);
                ui.printResponse(c.execute(tasks, ui, storage));
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printResponse(formatErrorMsg(e.getMessage()));
            } finally {
                ui.showLine();
            }
        }
    }
}
