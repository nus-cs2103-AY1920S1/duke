package duke;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import duke.repos.TaskRepo;
import duke.backend.Storage;
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
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private boolean isExit;
    private SimpleDateFormat formatter;
    private TaskRepo repo;

    Duke() {
    }
    /**
     * Main method that drives the running of the app. Creates new UI/Storage and ListManagers
     * @param filePath to access a pre-existing list (if-any)
     */
    public Duke(String filePath) throws IOException {
        isExit = false;
        this.formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        storage = new Storage(filePath);
        repo = new TaskRepo(storage);
    }

    /**
     * method to ask the UI to ask user for input and change String input into Command class.
     */
    private String run(String input) throws IOException, ParseException {
        String output = "";
        Command c = Parser.parse(input, formatter);
        output = c.execute(repo);
        isExit = c.isExit();
        return output;
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws IOException, ParseException {
       if (!this.isExit) {
           return this.run(input);
       } else {
           return "Program has already terminated.";
        }
    }

    public void init() {

    }
}
