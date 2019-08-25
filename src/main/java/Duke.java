import duke.command.Command;
import duke.logic.DukeException;
import duke.logic.Parser;
import duke.logic.Storage;
import duke.logic.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws FileNotFoundException {
        ui = new Ui();
        storage = new Storage(filePath);

        tasks = new TaskList(storage.load());

    }

    public void run() throws IOException, DukeException {
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

    public static void main(String[] args) throws DukeException,IOException {
        Duke duke = new Duke("data/tasks.txt");
        duke.run();
    }

}
