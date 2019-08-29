import duke.exception.DukeException;
import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.text.Parser;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class Duke {
    protected TaskList tasks;
    protected Storage storage;
    protected Ui ui;

    /**
     * Duke Constructor that takes in a filePath where application data would be stored.
     *
     * @param filePath Path of storage file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } finally {
            ui.setTasks(tasks);
        }
    }

    public static void main(String[] args) {
        Duke newDuke = new Duke("data/duke.txt");
        newDuke.run();
    }

    /**
     * Main run method. Application is in a constant loop until bye command changes isExit to true and
     * break out of the loop.
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
