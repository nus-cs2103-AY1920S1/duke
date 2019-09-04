package duke;

import duke.command.Command;
import duke.error.DukeException;

/**
 * main class.
 * */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Parser parser = new Parser();
    private Ui ui;
    private String filePath = "data/tasks.txt";

    /**
     * constructor of main class.
     * initialize ui, storage, tasklist objects
     *
     * @param filePath String of file location
     * */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList();
        }

    }

    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    public String showWelcome() {
        return ui.showWelcome();
    }

}
