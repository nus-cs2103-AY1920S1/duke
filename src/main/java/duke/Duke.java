package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.stage.Stage;

public class Duke extends Application {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Initialise new duke.Duke session.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    /**
     * Starts up the chat-box application.
     *
     * @param stage The stage to set the scene on.
     */
    @Override
    public void start(Stage stage) {

    }


    /**
     * Parses the user input and returns a response string by Duke.
     *
     * @param input The user input keyed in.
     * @return The response string generated.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}