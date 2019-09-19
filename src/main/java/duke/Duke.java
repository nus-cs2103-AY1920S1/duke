package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.ui.MainUi;
import duke.ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Starts Duke with specified filepath to saved list.
     * @param filePath filepath to read and write list.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Main loop of Duke. Ends if user inputs 'bye', otherwise continues.
     */
    public void run() {
        assert ui != null;
        assert storage != null;
        assert tasks != null;

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

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }

    /**
     * Returns duke.Duke with child class MainUi
     * @return duke.Duke meant for GUI interface. Uses MainUi instead of Ui class.
     */
    //@@author CarbonGrid
    public Duke usingMainUi() {
        ui = new MainUi();

        return this;
    }
    //@@author

    /**
     * (Event-Driven) Takes in GUI user input, parses the command,
     *   then returns the duke response.
     * @param input Command to be parsed by Duke
     * @return str Result of parsing
     */
    public String getResponse(String input) {
        assert input != null;
        assert ui instanceof MainUi;

        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.show(e.getMessage());
        } finally {
            return ((MainUi) ui).getResponse();
        }
    }
}

