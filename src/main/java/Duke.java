import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.DukeIllegalDescriptionException;
import duke.exceptions.DukeIllegalInputException;

/**
 * The main class of Duke chat bot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * The constructor of the Duke class, pass string value to the variable, load the file from hard disk and create
     * a new file if not existed.
     *
     * @param filePath The store location of the file.
     */
    private Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            create();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("/Users/auxin/duke/data/Tasks.txt");
        duke.greet();
        while (Ui.getFlag()) {
            duke.run();
        }
    }

    /**
     * To create a new folder and new file if not existed and set a new empty task list.
     */
    private void create() {
        storage.createFolder();
        storage.createFile();
        tasks = new TaskList();
    }

    /**
     * To greet the users.
     */
    private void greet() {
        ui.greet();
    }

    /**
     * To execute the main function of the program and print the exception if encountered.
     */
    private void run() {
        try {
            ui.scan();
        } catch (DukeIllegalInputException | DukeIllegalDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }
}
