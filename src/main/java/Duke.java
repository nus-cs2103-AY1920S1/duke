import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * A program named Duke.
 * Something like a Personal Assistant Chatbot that helps
 * a person keep track of various things, except it can only
 * understand archaic text commands of a specific structure.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    //public Duke(String filePath) {
    public Duke() {
        ui = new Ui();
        //storage = new Storage(filePath);
        storage = new Storage("F:\\CS2103\\duke\\data\\duke.txt");
        parser = new Parser();
        try {
            // Initialise taskList with AL of tasks
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            // If file does not exist, just create new taskList
            ui.showMessage("File in given path does not exist yet. Creating new list of tasks...");
            taskList = new TaskList();
        }
    }

    // Overrides Application#start() method with concrete implementation
    @Override
    public void start(Stage stage) {
        // Parameter Stage is primary stage that JavaFX provides

        // To contain text to show
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        // Create Scene and set its content
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        // Set the stage and show it
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Starts Duke program.
     * @throws IOException exception
     */
    public void run() throws IOException {
        ui.showGreetings();
        boolean inProgram = true;
        while (inProgram) {
            try {
                String userFullInput = ui.readUserInput();
                Command c = parser.parse(userFullInput, taskList);
                c.execute(taskList, ui, storage);
                inProgram = c.toContinueProgram();
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }


    public static void main(String[] args) throws IOException {
        //new Duke("F:\\CS2103\\duke\\data\\duke.txt").run();
        new Duke().run();
    }


    public TaskList getTaskList() {
        return taskList;
    }

}