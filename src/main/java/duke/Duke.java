package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import duke.command.CommandNotFoundException;
import duke.parser.IncorrectFileFormatException;
import duke.parser.IncorrectNumberOfArgumentsException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.parser.Parser;
import duke.command.Command;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Represents a Duke - interactive bot.
 * Contains functions to operate the bot.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(){

    }

    /**
     * Constructor for class Duke.
     *
     * @param filePath String of file path to read.
     */
    public Duke(String filePath) {
        Parser.initialize();
        storage = new Storage(filePath);

        ui = new Ui();

        try {
            tasks = new TaskList(storage.load());
        } catch (InvalidPathException i) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IncorrectFileFormatException | FileNotFoundException f) {
            ui.showLoadingError();
        } catch (NullPointerException n) {
            ui.showIndexError();
        }
    }

    /**
     * Runs the duke bot.
     */
    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();

                ui.showLine(); // show the divider line ("_______")

                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);

                isExit = c.isExit();

            } catch (IndexOutOfBoundsException o) {
                ui.showIndexError();
            } catch (NullPointerException n) {
                ui.showInputError();
            } catch (CommandNotFoundException c) {
                ui.showCommandNotFoundError();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IncorrectNumberOfArgumentsException e) {
                ui.showIncorrectNumberOfArgument();
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("C:\\Users\\user\\Desktop\\CS2103_Git\\duke\\data\\tasks.txt").run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}












