package duke;

import duke.command.Command;

import java.io.File;

/**
 * Class that facilitates transfer of user input to the system, and system output back to the user.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        String filePath = "data/tasks.txt";
        ui = new Ui();
        storage = new Storage(filePath);

        assert new File(filePath).exists();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.getLoadingError(); // return value not assigned
            tasks = new TaskList();
        }
    }

    /**
     * Returns system output from a given user input.
     *
     * @param input User input
     * @return String system output
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.getError(e.getMessage());
        }
    }

    /**
     * Returns welcome message of Ui.
     *
     * @return String welcome message
     */
    public String getWelcome() {
        return ui.getWelcome();
    }
}
