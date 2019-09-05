package duke.main;

import duke.component.Ui;
import duke.component.TaskList;
import duke.database.Storage;
import duke.command.Command;
import duke.component.Parser;
import duke.exception.DukeException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Duke class handle the initialise of the Duke Program,
 * database and exit of the program.
 *
 * @author TeoShyanJie
 *
 */
public class Duke extends Application {
    /** Database of the Duke Program. */
    private Storage storage;

    /** List of task. */
    private TaskList tasks;

    /** Ui of the Duke Program. */
    private Ui ui;

    /**
     * Duke program to initalise the Database, Ui and get the task list.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(ui);
        storage.initialise();
        tasks = new TaskList(storage.getSavedTask());
    }

    /**
     * To stimulate the Duke Program to run.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.showSpace();
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand, ui);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * To start the User interface of Duke Program.
     * @param stage Top level javafx container.
     */
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main function to start Duke Database.
     * @param args Arguments enter by user.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}