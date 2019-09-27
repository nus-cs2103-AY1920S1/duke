package main;

import command.Command;
import exception.DukeException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.FileNotFoundException;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction
 * with users.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Loads task list from hard disk if file is found.
     * Else create an empty task list.
     *
     * @param filePath directory where task list is stored
     */
    public Duke(String filePath) {
        ui = new Ui();
        ui.showWelcome();
        ui.printNonGuiDisplayMsg();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError("Could not find existing task list. Creating new one...");
            tasks = new TaskList();
        }
    }

    /**
     * Initializes Duke to use GUI if isGui is true
     * Loads task list from hard disk if file is found.
     * Else create an empty task list.
     *
     * @param filePath directory where task list is stored
     */
    public Duke(String filePath, boolean isGui) {
        this(filePath);
        if (isGui) {
            ui = new Ui(true);
            ui.showWelcome();
        }
    }

    public Ui getUi() {
        return ui;
    }

    /**
     * Collects input from command and executes relevant command
     * in GUI.
     *
     * @param input is the user input
     * @return String message to notify user of their input result
     */
    public String getResponse(String input) {
        try {
            Command command = new Parser().parse(input);
            command.execute(tasks, ui, storage);
            return ui.getDisplayMsg();
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            return ui.getDisplayMsg();
        }
    }

}
