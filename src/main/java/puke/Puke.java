package puke;

import puke.command.Command;
import puke.command.ResponseStrings;
import puke.storage.Storage;
import puke.task.TaskList;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main logical class, responsible for initialisation of data structures
 * and passing arguments to them for processing.
 */
public class Puke extends Application {
    private TaskList tasks;
    private Storage storage;

    String getResponse(String cmd) {
        try {
            ResponseStrings respStrings = new ResponseStrings();
            Command.parse(cmd).execute(tasks, respStrings, storage);
            return respStrings.toString();
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    Puke() {
        try {
            storage = new Storage("tasks.txt");
            tasks = new TaskList(storage.read());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    @Override
    public void start(Stage stage) {
    }
}
