package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.history.History;
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
    private History history;

    /**
     * Constructor for the Duke class.
     *
     * @param filePath Local filePath to the data storage file.
     */
    private Duke(String filePath) {
        ui = new Ui();
        ui.setDuke(this);
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        history = new History(storage, tasks);
    }

    public Duke() {
        this("src/main/data/duke.txt");
    }

    public String execute(String command) throws DukeException {
        Command c = Parser.parse(command);
        return c.execute(tasks, storage, history);
    }

    @Override
    public void start(Stage stage) {
        ui.start(stage);
    }
}