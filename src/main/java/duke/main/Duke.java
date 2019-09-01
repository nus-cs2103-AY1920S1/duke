package duke.main;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import java.io.FileNotFoundException;

/**
 * The duke robot's main class.
 */
public class Duke {
    private Storage storage; //The place of storage.
    private TaskList tasks; //The list of tasks.
    private Ui ui; //The user interaction.

    /**
     * The main function of the class.
     *
     * @param args Input arguments.
     */
    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }

    /**
     * Initiates the object.
     *
     * @param filePath The path root of the file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Operates the object.
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



}