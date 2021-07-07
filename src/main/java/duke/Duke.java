package duke;

import duke.command.Command;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Starting point of the application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Main driver function for the program.
     *
     * @param filePath Path to save events save file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Initializes and executes the application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parseUserInput(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Returns response to a userImage input.
     *
     * @param input User input
     * @return String representation of the result of a userImage input
     */
    public String getResponse(String input) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        final PrintStream old = System.out;
        ui.switchStream(ps);
        try {
            String fullCommand = input;
            Command c = Parser.parseUserInput(fullCommand);
            c.execute(tasks, ui, storage);
            if (c.isExit()) {
                Platform.exit();
            }
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
        System.out.flush();
        System.setOut(old);
        return baos.toString();
    }
}
