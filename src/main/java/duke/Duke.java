package duke;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import duke.ui.Ui;
import duke.backend.Storage;
import duke.backend.ListManager;
import duke.command.Command;
import duke.parser.Parser;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;


public class Duke {

    private Ui ui;
    private Storage storage;
    private ListManager listManager;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    Duke() {
    }
    /**
     * Main method that drives the running of the app. Creates new UI/Storage and ListManagers
     * @param filePath to access a pre-existing list (if-any)
     */
    private Duke(String filePath) {
        ui = new Ui();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        storage = new Storage(filePath, formatter);
        try {
            listManager = new ListManager(storage.load(), formatter);
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            listManager = new ListManager(formatter);
        }

    }

    /**
     * method to ask the UI to ask user for input and change String input into Command class.
     */
    private void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.inputCommand();
            ui.bar();
            Command c = Parser.parse(fullCommand);
            c.execute(listManager, ui, storage);
            isExit = c.isExit();
            ui.bar();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public void init() {

    }
}
