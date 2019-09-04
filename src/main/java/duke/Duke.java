package duke;

import duke.commands.Command;
import duke.commands.CommandResult;

import duke.data.TaskList;

import duke.exceptions.DukeException;

import duke.parser.Parser;

import duke.storage.Storage;

import duke.ui.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Implements the Duke chat bot.
 * @author Lim Yong Shen, Kevin
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Duke chat bot with the specified data file name.
     * @param fileName The specified data file name.
     */
    private Duke(String fileName) {
        ui = new Ui();
        storage = new Storage(fileName);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Constructs a Duke chatbot (used to run the Launcher class).
     */
    public Duke() {
    }

    /**
     * Runs the Duke chat bot until the exit command is given.
     */
    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                ui.showHorizontalBorder();
                Command command = Parser.parse(input);
                command.setTaskListToExecuteOn(tasks);
                CommandResult commandResult = command.execute();
                ui.showMessage(commandResult.getMessage());
                isExit = command.isExit();
                storage.save(tasks);
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            } finally {
                ui.showHorizontalBorder();
                ui.showEmptyLine();
            }
        }
        ui.close();
    }

    /**
     * Starts the specified JavaFX stage.
     * @param stage The specified JavaFX stage.
     */
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        helloWorld.setFont(new Font("Arial", 50.0)); // Sets the Label's font to Arial
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage
    }

    /**
     * Executes the program.
     * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {
        new Duke("src/main/java/duke/data/tasks.txt").run();
    }

}
