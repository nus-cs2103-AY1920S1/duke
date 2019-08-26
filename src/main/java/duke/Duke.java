package duke;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.parser.Parser;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Constructor. Creates Ui, Storage and TaskList object.
     * If the file does not exist, an error will be shown.
     * @param filePath Directory to the data file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, ui);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Shows the welcome message and gets Ui to show the current tasks in the list.
     * Reads in the command line by line, parse it and executes it.
     */
    public void run() {
        ui.showWelcome(taskList.getTasks());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
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
