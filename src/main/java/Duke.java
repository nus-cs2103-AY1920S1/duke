import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.command.Command;
import duke.parser.Parser;
import duke.exception.DukeException;

/**
 * Duke Class.
 */
public class Duke {
    /** Storage. */
    private Storage storage;
    /** TaskList to store user input. */
    private TaskList taskList;
    /** Ui for user interaction. */
    private Ui ui;

    /**
     * Constructor.
     * @param filePath File path of data file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        taskList = new TaskList();
        taskList.load(storage);
    }

    /**
     * Main Method.
     * @param args Arguments.
     */
    public static void main(String[] args) {
        new Duke("../data/tasks.txt").run();
    }

    /**
     * Runs Duke main program logic.
     */
    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(command);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
