import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.command.Command;
import duke.parser.Parser;
import duke.exception.DukeException;

/**
 * Duke Class.
 * Represents the main driver class of Duke.
 */
public class Duke {
    /** Storage to data file access and writes.. */
    private Storage storage;
    /** TaskList to store user input. */
    private TaskList taskList;
    /** Ui for user interaction. */
    private Ui ui;

    /**
     * Creates an instance of Duke.
     *
     * @param filePath File path of data file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        taskList = new TaskList(storage);
    }

    /**
     * Main Method.
     * Creates an instance of Duke and runs the logic.
     *
     * @param args Arguments.
     */
    public static void main(String[] args) {
        new Duke("../../../data/tasks.txt").run();
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
        ui.close();
    }
}
