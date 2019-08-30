package core;

import command.Command;
import exception.DukeException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import task.TaskList;

/**
 * Duke Chat Class.
 *
 * <p>A Personal Assistant Chatbot that helps to keep track of various things.
 *
 * @author Marcus Ong
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke Constructor.
     */
    public Duke() {}

    /**
     * Duke Constructor, auto-initialized.
     *
     * @param filePath String containing path of data file for Duke.
     */
    public Duke(String filePath) {
        initialize(filePath);
    }

    /**
     * Initializes Duke.
     *
     * @param filePath String containing path of data file for Duke.
     * @return reference to Duke object.
     */
    private Duke initialize(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        return this;
    }

    public static void main(String[] args) {
        new Duke().initialize(System.getProperty("user.dir") + "/data/duke.txt").run();
    }

    /**
     * Runs chat bot interaction.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Starts JavaFX application.
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Create a new Label control
        helloWorld.setStyle("-fx-font-size: 50");
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage
    }
}
