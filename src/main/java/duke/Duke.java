package duke;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.DukeWindow;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main Duke Program.
 */
public class Duke extends Application {


    private Storage storage;
    private TaskList tasks;
    private static final String FILE_PATH = "data/duke.txt";
    private DukeWindow mainUi;

    @Override
    public void init() throws Exception {
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainUi = new DukeWindow(primaryStage, storage, tasks);
        mainUi.show();
    }
}
