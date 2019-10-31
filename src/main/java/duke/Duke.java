package duke;

import duke.command.Command;

import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.History;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Duke is a personal assistant that helps manage tasks in a list.
 */
public class Duke {

    private static final String FILEPATH = ".\\data\\duke.txt";

    private boolean isExiting = false;

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private History history;

    /**
     * Starts a new Duke session, loading any existing tasks from previous sessions from the hard disk.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILEPATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
        history = new History(tasks);
    }

    /**
     * Takes a command by the user, parses and executes it, then returns a text response.
     * @param fullCommand The user's input as a String.
     * @return A String representing Duke's response.
     */
    public String getResponse(String fullCommand) {
        try {
            Command c = Parser.parseCommand(fullCommand);
            isExiting = c.isExit();
            if (isExiting) {
                exitDuke();
            }
            return c.execute(tasks, ui, storage, history);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Waits 2 seconds, then exits the app.
     */
    private void exitDuke() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                System.exit(0);
            }
        }, 2000);
    }

    /**
     * Gets the value of isExiting.
     * @return True if isExiting is true. False otherwise.
     */
    public boolean getIsExiting() {
        return isExiting;
    }
}
