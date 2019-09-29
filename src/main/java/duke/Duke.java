package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.IoDukeException;
import duke.parser.CommandParser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Terminates Duke using this command string.
     */
    static final String EXIT_COMMAND = "COMMAND_EXIT";

    /**
     * Creates a new instance of Duke.
     *
     * @param filePath The save file path
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();

            try {
                storage.createStorageFile();
            } catch (IoDukeException ioException) {
                // Suppress the error, as we only want to create the file.
            }
        }
    }

    private void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                if (input == null) {
                    break;
                }

                ui.showLine();
                Command c = CommandParser.parse(input);
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
     * Gets a response from Duke.
     *
     * @param input The input command
     * @return A response from Duke based on the input string
     */
    public String getResponse(String input) {
        if (input == null) {
            return null;
        }

        try {
            Command c = CommandParser.parse(input);
            c.execute(tasks, ui, storage);

            if (c.isExit()) {
                return EXIT_COMMAND;
            }
        } catch (DukeException e) {
            return e.getMessage();
        }

        return ui.getMessage();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
