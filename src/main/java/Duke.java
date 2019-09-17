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
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;


    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    @Override
    public void start(Stage stage) {

    }

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
            ui.setResponse(e.getMessage());
            ui.printResponse();
        }

    }

    public String getWelcomeMessage() {
        ui.setResponse("Hello! I am Duke\n"
                +
                "     What can I do for you?\n"
                +
                "     "
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
                "     What can I do for you?");
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
                ui.setResponse(e.getMessage());
                ui.printResponse();
            }
        }
    }

    public Ui getUi() {
        return ui;
    }

    public String getDukeResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            ui.printResponse();
            return ui.getResponse();
        } catch (DukeException e) {
            ui.setResponse(e.getMessage());
            ui.printResponse();
            return ui.getResponse();
        }

    }

    public static void main(String[] args) {
        new Duke().run();

    }


}
