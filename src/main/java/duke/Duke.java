package duke;

import duke.exception.DukeException;
import duke.ui.Ui;
import java.io.IOException;
import java.util.Scanner;
import duke.command.Command;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class duke to start the programme.
 */
public class Duke extends Application {
    private TaskList tasks;
    private Saved savedFile;
    private Scanner scan;
    private Ui ui;

    /**
     * Creates a Duke object.
     * Initialise the storage, task list and ui objects.
     */
    public Duke() {
        savedFile = new Saved("src/main/java/data.txt");
        ui = new Ui();
        try {
            tasks = new TaskList(savedFile.loadData());
        } catch (IOException e) {
            tasks = new TaskList();
        }
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
                cmd.execute(tasks, ui);
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
        new Duke().run();
    }

    /**
     * Gets response from the user.
     */
    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input);
            return cmd.execute(tasks, ui);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    @Override
    public void start(Stage start) {
    }
}