package duke;

import duke.exception.DukeException;
import duke.ui.Ui;
import java.io.IOException;
import java.util.Scanner;
import duke.command.Command;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke extends Application {
    private TaskList tasks;
    private Saved savedFile;
    private Scanner scan;
    private Ui ui;


    /**
     * Creates a Duke object.
     * Initialise the storage, task list and ui objects.
     *
     * @param filePath the local path to the storage file
     * @throws IOException filePath is inaccessible or cannot be found
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        savedFile = new Saved(filePath);

        tasks = new TaskList(savedFile.loadData());
    }

    /**
     * Obtains user input from command line, parses it and executes the commands.
     * Shows errors to the user in case of invalid input.
     * Saves updated tasks back into the local file
     *
     * @throws IOException filePath is inaccessible or cannot be found
     */
    public void run() throws IOException {
        String input;
        Command cmd;

        ui.printIntro();

        Boolean isBye = false;
        while (!isBye) {
            try {
                input = ui.scanCmd();
                ui.printLine();
                cmd = Parser.parse(input);
                cmd.execute(tasks, ui, savedFile);
                isBye = cmd.isBye();
            } catch (DukeException error) {
                ui.printError(error.getMessage());
            }
            ui.printLine();
        }
        savedFile.saveToFile(tasks.getTaskArrayList());

    }

    /**
     * Required main method.
     *
     * @param args command line arguments
     * @throws IOException local file is inaccessible or cannot be found
     */
    public static void main(String[] args) throws IOException {
        new Duke("src/main/java/data.txt").run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}