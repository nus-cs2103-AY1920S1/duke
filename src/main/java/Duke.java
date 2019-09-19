import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormatSymbols;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Main class that runs the Duke program.
 */
public class Duke {
    private Storage storage = new Storage();
    private TaskList tasks = new TaskList();
    private Ui ui = new Ui();

    /**
     * Constructs a new Duke object.
     */
    public Duke() {
        try {
            storage.load(tasks);
        } catch (DukeException e) {
            ui.setErrorResponse(e.getMessage());
            ui.printResponse();
        }

    }

    /**
     * Gets the welcome message on startup.
      * @return welcome message, including list of tasks
     */
    public String getWelcomeMessage() {
        ui.setWelcomeResponse("Hello! I am Duke\n"
                +
                "What can I do for you?\n"
                +
                "\n"
                +
                "These are your current tasks:\n "
                +
                this.tasks.listTasks());
        return ui.getResponse();
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        Parser parser = new Parser();
        Ui.print("Hello! I am Duke\n"
                +
                "What can I do for you?");
        this.tasks.listTasks();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.setInput();
                String input = ui.getInput();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                ui.printResponse();
                isExit = c.isExit();
                storage.save(tasks);
            } catch (DukeException e) {
                ui.setErrorResponse(e.getMessage());
                ui.printResponse();
            }
        }
    }

    /**
     * Gets the UI Duke is using.
     * @return Ui object Duke is using
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Gets Duke's response on user's input.
     * @param input user's input
     * @return Duke's response
     */
    public String getDukeResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            ui.printResponse();
            return ui.getResponse();
        } catch (DukeException e) {
            ui.setErrorResponse(e.getMessage());
            ui.printResponse();
            return ui.getResponse();
        }

    }

    public static void main(String[] args) {
        new Duke().run();

    }


}
