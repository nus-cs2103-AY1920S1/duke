package seedu.duke;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Main class of Duke application.
 */
public class Duke {
    private static final String DATA_PATH = "./duke_data.txt";

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private Scanner sc;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(DATA_PATH);
        sc = new Scanner(System.in);
        parser = new Parser(sc, storage);
    }

    /**
     * TODO
     * this should not exist anymore
     */
    void run() {
        ui.greeting();
        int response;
        do {
            ui.printLine();
            String command = sc.next();
            ui.printBreakLine();
            response = parser.process(command);
            if (response == -1) {
                ui.goodbye();
            }
            ui.printBreakLine();
        } while (response != -1);
    }

    /**
     * TODO
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
