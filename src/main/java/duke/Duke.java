package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.stage.Stage;

public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit = false;

    /**
     * Constructor for the Duke class.
     *
     * @param filePath Local filePath to the data storage file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        ui.setDuke(this);
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Duke() {
        this("src/main/data/duke.txt");
    }

    public boolean execute(String command) throws DukeException {
        Command c = Parser.parse(command);
        c.execute(tasks, ui, storage);
        return c.isExit();
    }

    @Override
    public void start(Stage stage) {
        ui.start(stage);
    }
}