package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import duke.task.Task;
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

    private Storage storage;
    private ListManager listManager;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private boolean isExit;

    Duke() {
    }
    /**
     * Main method that drives the running of the app. Creates new UI/Storage and ListManagers
     * @param filePath to access a pre-existing list (if-any)
     */
    Duke(String filePath) throws IOException {
        isExit = false;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        storage = new Storage(filePath, formatter);
        try {
            listManager = new ListManager((ArrayList<Task>) storage.load(), formatter);
        } catch (FileNotFoundException e) {
            listManager = new ListManager(formatter);
        }

    }

    /**
     * method to ask the UI to ask user for input and change String input into Command class.
     */
    private String run(String input) {
        String output = "";
        Command c = Parser.parse(input);
        output = c.execute(listManager, storage);
        isExit = c.isExit();
        return output;
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
       if (!this.isExit) {
           return this.run(input);
       } else {
           return "Program has already terminated.";
        }
    }

    public void init() {

    }
}
