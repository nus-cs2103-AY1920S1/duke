package duke;

import java.io.File;
import java.io.IOException;

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
     */
    public Duke() throws IOException {
        ui = new Ui();
        ui.setDuke(this);
        new File("duke.txt").createNewFile();
        storage = new Storage("duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        history = new History(storage, tasks);
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