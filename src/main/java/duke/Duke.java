package duke;

import command.Command;
import utils.TaskList;
import utils.Ui;
import utils.Parser;
import utils.Storage;
import utils.Alias;
import error.DukeException;

/**
 * main class of Duke program.
 * */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Alias alias;
    /* file location to store text file */
    private String filePath = "data/tasks.txt";

    /**
     * Creates a duke instance that initializes Ui, Storage and load data in Tasklist.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        alias = new Alias();
        try {
            alias.load();
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    /**
     * Parses in user's input, execute and returns a response to GUI.
     *
     * @param input String of user's input.
     * @return String as response to user.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Calls to Ui method to generate welcome dialog.
     */
    public String showWelcome() {
        return ui.showWelcome();
    }

}
