package seedu.duke;

import duke.exception.DukeException;
import duke.command.Command;
import duke.command.Parser;
import duke.handler.Storage;
import duke.handler.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Duke is the main class that launches the chat bot when it is run.
 */
public class Duke extends Application {

    /**
     * This is the storage class which does the saving and loading of the files.
     */
    private Storage storage;

    /**
     * This is the task list class that contains the list of tasks.
     */
    private TaskList tasks;

    /**
     * The ui class handles the output to the user.
     */
    private Ui ui;

    /**
     * Creates a new chat bot with a specified file path.
     * If the specified file contains pre-saved tasks, it will be loaded.
     * @param filePath The path of the file that is used to save and load the tasks.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the app.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                Scanner sc = new Scanner(System.in);
                String fullCommand = sc.nextLine();
                ui.printLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks.getList(), ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.showError("The command is invalid!");
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}

