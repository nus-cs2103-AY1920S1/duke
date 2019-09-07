package duke;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialise Duke with stated filePath.
     *
     * @param filePath filePath to read and save list data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.show(e.getMessage());
            tasks = new TaskList();
        }
    }

    public Duke usingMainUi() {
        this.ui = new MainUi();
        return this;
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Main loop of Duke.
     */
    public void run() {
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                Command c = Parser.parse(ui.readCommand());
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.show(e.getMessage());
            }
        }
    }


    /**
     * Tries to parse and execute input as Command to Duke.
     * Any exceptions should be included within String response.
     *
     * @param input Parses and Executes input as Command to Duke
     * @return Duke's response as String
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
