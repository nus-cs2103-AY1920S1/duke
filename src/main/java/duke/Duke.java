package duke;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main logical class, responsible for initialisation of data structures
 * and passing arguments to them for processing.
 */
public class Duke extends Application {
    private TaskList tasks;
    private Storage storage;

    String getResponse(String cmd) {
        try {
            Command c = Command.parse(cmd);
            return c.execute(tasks, storage);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    Duke() {
        storage = new Storage("tasks.txt");
        try {
            tasks = new TaskList(storage.read());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    @Override
    public void start(Stage stage) {
    }
}
