import java.io.FileNotFoundException;
import duke.command.*;
import duke.exception.*;
import duke.parser.*;
import duke.storage.*;
import duke.task.*;
import duke.ui.*;

/**
 * This is a Personal Assistant Chatbot named Duke. It acts like a task manager to help a person keep track of
 * tasks to do, various deadlines and events. The name Duke was chosen in honor of Duke, the Java Mascot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    /**
     * Initialises the Duke class with a certain filepath.
     * Creates a new user interface and storage.
     * Load any existing tasks from the file found in filepath to a list.
     * If no file is found in the filepath, shows an error and creates a new list.
     *
     * @param filePath location of file containing previously saved list of tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasklist = new TaskList(storage.loadFile());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasklist = new TaskList();
        }
    }

    /**
     * Runs the Duke application.
     * Shows a welcome message and starts receiving commands from user inputs.
     * Executes commands accordingly.
     */
    public void run() {
        ui.showWelcome();
        boolean isTerminated = false;
        while (!isTerminated) {
            try {
                String fullCommand = ui.readCommand();
                ui.sendLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasklist, ui, storage);
                isTerminated = c.isTerminated();
            } catch (DukeException e) {
                ui.sendMessage(e.toString());
            } finally {
                ui.sendLine();
            }
        }
    }

    /**
     * Represents the main class of the Duke application.
     * Starts up a new Duke application with a filepath showing location of previously saved tasks.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
