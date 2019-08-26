import duke.command.Command;
import duke.command.Parser;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 * Drives the Duke bot
 * This is the main driver class and entry point.
 */
public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException fnfe) {
            tasks = new TaskList();
            ui.exposeError("File not found at " + filePath + ", starting with a clean slate.");
        } catch (IOException|ParseException e) {
            tasks = new TaskList();
            ui.exposeError("Unable to read file at " + filePath + ", starting with a clean slade");
        }
    }

    public void run() {

        this.ui.printWelcomeMsg();

        boolean isGoodbye = false;

        while (!isGoodbye) {
            try {
                String fullInput = ui.readInput();
                Command c = Parser.parse(fullInput);
                c.execute(tasks, ui, storage);
                isGoodbye = c.isExit();
            } catch (DukeException de) {
                ui.exposeError(de.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke dukeInstance = new Duke("data/duke.txt");
        dukeInstance.run();
    }

}
