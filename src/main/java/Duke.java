import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import javafx.scene.control.Label;

/**
 * Represents the personal assistant and contains JavaFX details for the application.
 *
 */
public class Duke {

    private TaskList list;
    private Ui ui;
    private Parser parser;
    private Storage storage;

    /**
     * Creates a duke object which stores data in the specified path .
     *
     */
    public Duke() throws FileNotFoundException, ParseException {
        list = new TaskList();
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("data/Duke.txt");
        // file contains user data
        File file = new File("data/Duke.txt");
        //read and load the existing data into the task list.
        list.readDataFromFile(file,parser);

    }

    String getResponse(String dukeText) throws IOException, ParseException {
        return parser.readUserCommand(dukeText,ui,list,storage);
    }



}
