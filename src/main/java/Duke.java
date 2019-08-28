import Duke.exception.DukeException;
import Duke.parser.Parser;
import Duke.storage.Storage;
import Duke.task.*;
import Duke.ui.Ui;

/**
 * Duke is a chat based task manager that keeps track of the Tasks keyed in by the user.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a Duke object.
     * @param filePath the file path of a file that is written to and from when the program is executed.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.toString());
            tasks = new TaskList();
        }
    }

    /**
     * Starts Duke.
     */
    public void run() {
        Parser parser = new Parser(tasks);
        storage.write(parser.start());
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
