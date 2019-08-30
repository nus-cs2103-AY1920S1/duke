
import java.io.IOException;

/**
 * Duke is a to-do list that allows users to store todos, deadlines and events. Users are able to enter several
 * commands, such as list to list all tasks, done to mark certain tasks as done, and add and delete tasks.
 */
public class Duke {

    /** Storage object that access the data file. */
    private Storage storage;

    /** TaskList object that accesses the ArrayList of Tasks */
    private TaskList tasks;

    /** UI interface of Duke */
    private Ui ui;

    /**
     * Instantiates Duke that contains its own UI, storage and TaskList.
     *
     * @param filePath The path at which the storage file is stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

    /**
     * Runs the Duke UI and executes commands based on user input.
     */
    public void run() {
        ui.showWelcome();
        String fullCommand = ui.readCommand();
        while (!fullCommand.equals("bye")) {
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
            fullCommand = ui.readCommand();
        }

        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showError(e.getMessage());
        } finally {
            ui.exit();
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
