
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

/**
 * Duke is a to-do list that allows users to store todos, deadlines and events. Users are able to enter several
 * commands, such as list to list all tasks, done to mark certain tasks as done, and add and delete tasks.
 */
public class Duke {

    /**
     * Storage object that access the data file.
     */
    private Storage storage;

    /**
     * TaskList object that accesses the ArrayList of Tasks
     */
    private TaskList tasks;

    /**
     * UI interface of Duke
     */
    private Ui ui;


    public Duke() {
        this("duke.txt");
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

    /**
     * Runs the Duke UI and executes commands based on user input.
     */
    public void run() {
        ui.showWelcome();
        String fullCommand = ui.readCommand();
        while (!fullCommand.equals("bye")) {
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
            fullCommand = ui.readCommand();
        }

        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showError(e.getMessage());
        } finally {
            ui.exit();
        }
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        String result = "";
        try {
            if (!input.equals("bye")) {
                Command c = Parser.parse(input);
                result = c.execute(tasks);
            } else {
                result = ui.exit();
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } finally {
            return result;
        }

    }
}
