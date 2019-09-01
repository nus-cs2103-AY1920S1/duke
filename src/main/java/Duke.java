import duke.command.Command;

import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various tasks.
 */
public class Duke extends Application {
    /** User Interface of Duke that handles input and output to and from the command line. */
    private Ui ui;
    /** Storage where the Tasks are retrieved from and stored to. */
    private Storage storage;
    /** List of Tasks Duke is currently tracking. */
    private TaskList tasks;

    /**
     * Constructor for Duke that instantiates the Ui and Storage classes.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Runs and starts the Duke chatbot program.
     */
    public void run() {
        try {
            tasks = storage.getTasks();
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.printError(e);
            tasks = new TaskList();
        }

        ui.printGreeting();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                Command c = Parser.parse(input);
                isExit = c.isExit();
                c.execute(tasks, ui, storage);
            } catch (DukeException e) {
                ui.printError(e);
            }
        }

        ui.printExit();
    }
}
