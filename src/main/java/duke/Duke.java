package duke;

import duke.command.Command;
import duke.command.CommandParser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.DukeApplication;
import duke.ui.MainWindow;
import javafx.application.Application;

/**
 * Main class of the Duke app.
 * Launches the JavaFX DukeApplication.
 * Provides an abstraction of a running duke instance, with a method
 * to process input.
 */
public class Duke {

    /** The directory name to use in construction of the storage object. */
    private static final String RECURSIVE_PARENT_DIR_NAME = "data";
    /** The greeting message to show on application launch. */
    private static final String GREETING_MSG = " Hello! I'm Duke\n"
            + " What can I do for you?\n";
    /** The storage object to use for task-disk storage. */
    private Storage storage;
    /** The MainWindow object to use for displaying output to the user. */
    private MainWindow ui;
    /** The TaskList object to use for in-memory task storage. */
    private TaskList tasks;

    /**
     * Launches the DukeApplication JavaFX user interface.
     * Does not use any command line arguments currently.
     *
     * @param args Array of command line string arguments.
     */
    public static void main(String[] args) {
        Application.launch(DukeApplication.class);
    }

    /**
     * Constructor of the Duke instance.
     * Initializes the various instance properties.
     *
     * @param ui The MainWindow with which to initialise this duke instance.
     */
    public Duke(MainWindow ui) {
        this.ui = ui;
        ui.showMessage(GREETING_MSG);
        this.storage = new Storage(RECURSIVE_PARENT_DIR_NAME, ui);
        this.tasks = new TaskList();
        this.storage.loadTasksToList(tasks);
    }

    /**
     * Processes a string response for Duke from the user.
     * Takes a string input and creates a command, and then executes it.
     * Also prints any error messages resulting from Duke to the ui.
     *
     * @param input String input from the user.
     */
    public void processInput(String input) {
        try {
            Command command = CommandParser.parseCommand(input);
            command.execute(tasks, ui, storage);
        } catch (DukeExceptions ex) {
            ui.showMessage(ex.getDisplayMsg());
        }
    }
}