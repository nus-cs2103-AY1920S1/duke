package duke;

import duke.command.Command;

import duke.core.DukeException;
import duke.core.Parser;
import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

import java.io.File;

/**
 * Represents <code>Duke</code>, a Personal Assistant Chatbot that helps a 
 * person to keep track of various things.
 */
public class Duke {

    /**
     * A <code>Storage</code> object that deals with loading tasks from a local
     * file and saving tasks in the file.
     */
    private Storage storage;

    /**
     * A <code>TaskList</code> object that deals with operations on tasks in 
     * the list.
     */
    private TaskList tasks;

    /** A <code>Ui</code> object that deals with interactions with the user. */
    private Ui ui;

    /**
     * Constructs a <code>Duke</code> object with a default file path.
     * Initializes user interaction system and loads tasks from the file.
     */
    public Duke() {
        ui = new Ui();
        assert new File("data/tasks.txt").exists() : "tasks.txt not found";
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Constructs a <code>Duke</code> object with a specific file path.
     * Initializes user interaction system and loads tasks from the file.
     *
     * @param filePath A string that represents the path of the local file
     *          used for storing tasks.
     */
    private Duke(String filePath) {
        ui = new Ui();
        assert new File(filePath).exists() : "File not found";
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            ui.showLoadingSuccess();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Shows the result of loading past tasks from the local file.
     *
     * @return A string that represents success or failure of loading
     *      past tasks.
     */
    public String loadTasks() {
        try {
            tasks = new TaskList(storage.load());
            return ui.showLoadingSuccessGui();
        } catch (DukeException e) {
            tasks = new TaskList();
            return ui.showLoadingErrorGui();
        }
    }

    /**
     * Runs the <code>Duke</code> program that continuously reads, parses and 
     * executes user input until a "bye" message is received.
     */
    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
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

    /**
     * Kick-starts the <code>Duke</code> program by passing in a specific file
     * path.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.executeGui(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showErrorGui(e.getMessage());
        }
    }
}
