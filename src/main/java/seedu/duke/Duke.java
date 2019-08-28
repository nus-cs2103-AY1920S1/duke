package seedu.duke;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.util.Scanner;

/**
 * Main class of Duke application.
 */
public class Duke {
    private static final String DATA_PATH = "./duke_data.txt";

    private Ui ui;
    private Storage storage;
    private Parser parser;

    /**
     * Constructor.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(DATA_PATH);
        parser = new Parser(storage);
    }

    /**
     * Returns the response from the parser.
     * @param input The input from the user.
     */
    public String getResponse(String input) {
        return parser.process(input);
    }
}
