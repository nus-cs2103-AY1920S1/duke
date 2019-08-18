package duke;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.textual.Parser;
import duke.ui.Ui;
import java.io.FileNotFoundException;

/**
 * The main class.
 */
class Main {
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Storage storage;

    /**
     * Initializes a new Duke session.
     */
    private Main() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");

        try {
            tasks = new TaskList(storage.read());
        } catch (FileNotFoundException e) {
            ui.printError("Could not read tasks from disk, starting from empty...");
            tasks = new TaskList();
        }

        parser = new Parser();
    }

    /**
     * Starts up the initialized Duke session.
     */
    private void run() {
        boolean hasExited = false;
        ui.printWelcome();
        while (!hasExited) {
            try {
                Command c = parser.parseLine();
                c.execute(tasks, ui, storage);
                hasExited = c.isExit();
            } catch (IllegalArgumentException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
