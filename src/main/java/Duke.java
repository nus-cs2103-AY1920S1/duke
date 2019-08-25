import command.Command;
import storage.Storage;
import exception.DukeException;
import tasks.TaskList;
import ui.Parser;
import ui.Ui;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * constructor for Duke.
     * @param filePath which specifies the path for the .txt file
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
     * Instantiate a Duke object and run it.
     * @param args standard main args
     */
    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }

    /**
     * Running process of Duke.
     */
    public void run() {
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
}
