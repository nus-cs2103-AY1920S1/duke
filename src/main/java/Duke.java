import Commands.Command;
import Exceptions.DukeException;
import Parser.Parser;
import Storage.Storage;
import Tasks.TaskList;
import Ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor of the program Duke.
     * @param filePath path of the file that contains the list of stored tasks
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) { // Supposed to be Exceptions.DukeException
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Displays a welcome message and receives user commands continuously until command 'Bye' is entered.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
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

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}