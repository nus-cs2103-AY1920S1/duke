package duke;

import duke.dukeexception.DukeException;
import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class that serves as the main driver for the Duke application.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Class constructor that specifies file path to load storage from.
     *
     * @param filepath File path to load storage from.
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
    }

    private void run() {
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
        ui.printLogoAndGreet();
        String command = ui.readLine();
        Command c;
        boolean isDone = false;
        while (!isDone) {
            try {
                c = Parser.parse(command);
                isDone = c.isExit();
                c.execute(taskList, ui, storage);
            } catch (DukeException de) {
                ui.printDukeError(de.getMessage());
            }
            if (!isDone) {
                command = ui.readLine();
            }

        }
        //exit program
        ui.closeInput();
        if (storage.storageUpdated()) {
            try {
                ui.printWritingChanges();
                storage.writeToDisk(taskList);
                ui.printDoneWriting();
            } catch (DukeException de) {
                ui.showWritingError();
            }
        }
        ui.printGoodbye();
    }

    /**
     * Runs the main Duke program
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
