package duke;

import command.Command;
import exception.DukeException;
import parser.Parser;
import storage.FakeStorage;
import storage.Storage;
import task.TaskList;
import task.UndoStack;
import ui.Ui;

import java.io.IOException;

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
        ui.showWelcome();
        try {
            storage = new Storage("data/tasks.txt");
        } catch (IOException e) {
            ui.showStorageError();
            storage = new FakeStorage();
        }

        assert storage != null : "Storage should not be NULL";

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showTaskLoadingError();
            tasks = new TaskList();
        }

        UndoStack.initialize();
    }

    /**
     * main run method.
     */
    public String run(String command) {
        try {
            Command c = Parser.parse(command);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}