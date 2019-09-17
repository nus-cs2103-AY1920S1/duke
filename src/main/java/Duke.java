import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.dukeexception.DukeException;
import duke.command.Command;
import duke.parser.Parser;

/**
 * Main driver class for Duke application. Duke application helps the user to manage tasks and allows the user to
 * add, remove, mark a task as completed, list all tasks and find all tasks containing a keyword.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final String INDENT_SPACING = "    ";
    private static final String ROOT_DIRECTORY = "C:/Users/gbrls/OneDrive/Desktop/duke-master/src/main/java/duke/";

    /**
     * Loads a save file and generates a new Ui, Storage and TaskList object stored as instance variables.
     * @param filePath file path corresponding to the location of the save file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Handles user input and
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(ROOT_DIRECTORY + "data/tasks.text").run();
    }



}