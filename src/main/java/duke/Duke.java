package duke;

import duke.command.Command;

import duke.exception.DukeException;
import duke.exception.DukeIOException;

import duke.module.Parser;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * <h1>Duke</h1>
 * Stores user-defined tasks.
 *
 * @author  Kyungho Min
 * @version v0.1
 * @since   2019-09-01
 */
public class Duke extends Application {

    /** Handles user inputs and outputs. */
    private Ui ui;
    /** Stores the tasks inputted by the user. */
    private TaskList taskList;
    /** Saves the tasks in the TaskList to use at the next boot up. */
    private Storage storage;

    /**
     * Initializes the necessary modules to run the Duke application.
     *
     * @throws DukeIOException When an error occurs during the input-output process or
     * during the parsing of the save file
     */
    public Duke() throws DukeIOException {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList(storage.load());
    }

    /**
     * Runs the Duke application.
     */
    public void run() {
        // Greet the user
        this.ui.greet();

        // Handle user input
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = this.ui.readCommand();
                String description = this.ui.readDescription();
                Command c = Parser.parseToCommand(command, description);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                this.ui.printToUser(e.getMessage());
            }
        }
    }

    @Override
    public void start(Stage stage) {
        Label duke = new Label("DUKE"); // Creating a new Label control
        Scene scene = new Scene(duke, 500, 400); // Setting the scene to be our Label

        stage.setTitle("DUKE");
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
    
}