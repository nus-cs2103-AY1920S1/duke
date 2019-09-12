package duke;

import command.Command;
import exception.DukeException;
import parser.Parser;
import storage.FakeStorage;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Main Duke bot class.
 * Used to instantiate your personal Duke bot.
 * Currently the file path for record savings is hard-coded.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke class.
     */
    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage("data/tasks.txt");
        } catch (DukeException e) {
            System.err.println(e);
            System.out.println("You can still use Duke, but progress will not be saved");
            storage = new FakeStorage();
        }

        assert storage != null : "Storage should not be NULL";

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(ui.showLoadingError());
            tasks = new TaskList();
        }
    }

    /**
     * main run method.
     */
    public String run(String command) {
        try {
            Command c = Parser.parse(command);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Outputs a welcome message when Duke is started.
     * @return the welcome message as String
     */
    public String showWelcome() {
        return ui.showWelcome();
    }
}