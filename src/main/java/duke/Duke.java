package duke;

import duke.command.Command;
import duke.task.TaskList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Main class for the Duke application.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private TextUi ui;

    /**
     * Provides a constructor with zero parameters for JavaFX Launcher
     * to use.
     */
    public Duke() {
        // empty constructor for JavaFX to work
    }

    /**
     * Sets up Duke's user interface, storage, and task list.
     *
     * @param filePath  Path to data file
     */
    public Duke(String filePath) {
        ui = new TextUi();
        storage = new HardDiskStorage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Sets the stage and scene for JavaFX, using the given Stage.
     *
     * @param stage     Stage to be set
     */
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Welcome to Duke!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show(); 
    }

    /**
     * Runs the main application by interacting with user input.
     *
     * <p>Duke begins by printing a welcome message. Subsequently, it scans
     * for user input, then validates and processes it accordingly. This is
     * repeated until the command to exit ("bye") is received.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
                // TODO: Add "help" feature: list all supported commands
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Initialises and runs the Duke application.
     *
     * @param args  Standard arguments
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
