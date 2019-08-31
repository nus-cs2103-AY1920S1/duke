package duke;
import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Duke class is the driver class.
 *
 * @author scwaterbear
 */
public class Duke {

    /** References to important objects */
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class constructor.
     *
     * @param filePath location of file to load and store data.
     */
    private Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Main method for Duke.
     */
    public static void main(String[] args) {
        new Duke("/Users/stephenchua/duke/src/main/data/duke.txt").run();
    }
}

