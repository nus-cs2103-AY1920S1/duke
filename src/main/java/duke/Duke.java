package duke;

import duke.command.Command;

import duke.core.DukeException;
import duke.core.Parser;
import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

import java.io.File;
import java.net.URISyntaxException;

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
        try {
            //@@author Zarkonnen-reused
            //Adapted from https://stackoverflow.com/a/320542
            String parentPath = new File(this.getClass()
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
                    .getPath())
                    .getParent();
            //@@author
            storage = new Storage(parentPath + "/DukeStorage/tasks.txt");
            tasks = new TaskList();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the result of loading past tasks from the local file.
     *
     * @return A string that represents success or failure of loading
     *      past tasks.
     */
    public String loadPastTasks() {
        try {
            tasks = new TaskList(storage.load());
            return ui.showLoadingSuccess();
        } catch (DukeException e) {
            tasks = new TaskList();
            return ui.showError(e);
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }
}