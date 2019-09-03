import Exception.DukeException;
import Tasks.Task;
import Tasks.TaskList;
import Util.Storage;
import Util.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;
/**
 * Represents the main class for the project where the main method will run
 */
public class Duke extends Application {
    public static ArrayList<Task> storedTasks = new ArrayList<>();
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

//    public static void main(String[] args) throws Exception {
//        new Duke("data/duke.txt").run();
//    }

    /**
     * Constructs a Duke object which will initialise 1)storage, 2)tasks 3)Ui class..
     *
     * @param filePath is the file directory for where duke.txt is saved
     */
    public Duke(String filePath) throws Exception, DukeException {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile());
            ui = new Ui(tasks, storage);
        } catch (Exception e) {
            throw new DukeException("File is empty");
        }
    }
    public Duke(){

    }

    /**
     * runs the Ui read input to take in data from user.
     *
     */

    public void run() throws Exception{
        ui.readInput();
        storage.closeFile();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
