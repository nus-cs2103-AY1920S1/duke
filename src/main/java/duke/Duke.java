package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeIoException;
import duke.storage.Storage;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Main Driver class housing the infinite loop.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = storage.readFromDisk(); // leave index 0 empty for clarity
        } catch (DukeIoException e) {
            ui.showError(e);
            this.taskList = new TaskList(); // only load the taskList if no error
        }
    }

    // JavaFX GUI won't run without this.
    public Duke() { }

    /**
     * The main loop for Duke.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * The entry point for the JavaFX application.
     *
     * @param primaryStage the stage to render the JavaFX nodes.
     */
    @Override
    public void start(Stage primaryStage) {
        Label helloWorld = new Label("Hello World!"); // Create a new label control
        helloWorld.setFont(new Font("Arial", 50));
        Scene scene = new Scene(helloWorld); // Set the scene to be our Label

        primaryStage.setScene(scene); // Set the stage to show our screen
        primaryStage.show(); // Render the stage
    }
}
