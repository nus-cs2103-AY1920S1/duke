import java.io.FileNotFoundException;

import duke.command.Command;
import duke.parser.Parser;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * This is a Personal Assistant Chatbot named Duke. It acts like a task manager to help a person keep track of
 * tasks to do, various deadlines and events. The name Duke was chosen in honor of Duke, the Java Mascot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasklist;
    private Ui ui;
    private boolean isLoaded;

    /**
     * Initialises the Duke class.
     * Creates a new user interface and storage.
     * Load any existing tasks from the file found in filepath to a list.
     * If no file is found in the filepath, shows an error and creates a new list.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        try {
            tasklist = new TaskList(storage.loadFile());
            isLoaded = true;
        } catch (FileNotFoundException e) {
            tasklist = new TaskList();
            isLoaded = false;
        }
    }

    /**
     * Gets welcome message.
     *
     * @return welcome message.
     */
    public String getWelcome() {
        return ui.showWelcome();
    }

    /**
     * Shows whether data has been successfully loaded.
     *
     * @return loaded successfully or loading failed.
     */
    public String getLoaded() {
        if (isLoaded) {
            return ui.showLoaded();
        } else {
            return ui.showLoadingError();
        }
    }

    /**
     * Starts receiving commands from user inputs.
     * Executes commands accordingly.
     * Generates a response to user inputs.
     */
    protected String getResponse(String input) {
        boolean isTerminated;
        String result;
        try {
            Command c = Parser.parse(input);
            result = c.execute(tasklist, ui, storage);
            isTerminated = c.isTerminated();
        } catch (Exception e) {
            return e.toString();
        }
        if (isTerminated) {
            return ui.sendBye();
        }
        return result;
    }

}
