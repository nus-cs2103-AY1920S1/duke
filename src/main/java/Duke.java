import duke.UI.Ui;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    ArrayList<Task> tempStore = new ArrayList<Task>();

    /**
     * Constructor for Duke instance.
     * @param filepath file path giving the location of the file
     */
    public Duke(String filepath) {
        storage = new Storage(filepath);
        //initialise an arraylist to store Tasks
        TaskList store = new TaskList(storage.createTasksFromFile(), storage);
        this.parser = new Parser(store);
    }

    /**
     * Driver method for Duke instance.
     * calls parser to parse user input accordingly
     */

    public void run() {
        parser.readUserInput();
    }

    /**
     * Entry point of program, instantiates Duke instance.
     * file input/output is loaded from data/duke.txt
     * @return void
     */
    public static void main(String[] args) {
        Duke temp = new Duke("data/duke.txt");
        Ui.showGreeting();
        temp.run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }


}
