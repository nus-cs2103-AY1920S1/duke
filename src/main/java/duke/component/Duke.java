package duke.component;

import duke.command.Command;
import duke.exception.DukeException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;


/**
 * Represents chatbot Duke with main method.
 * The 'Duke' class supports operators (i) allows user interaction with chatbot.
 */
public class Duke {

    /**
     * Store and load tasks from text in text file.
     */
    private Storage storage;

    /**
     * Task List of tasks.
     */
    private TaskList tasks;

    /**
     * User Interface that takes care of user interaction with chatbot.
     */
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;


    /**
     * Creates a new instance of Duke with file path of text file.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    public String getResponse(String input) {
        try {
            Parser parser = new Parser();
            Command c = parser.parse(input);

            return c.execute(tasks, ui, storage);

        } catch (DukeException e) {
            return (e.getMessage());
        }

    }


}
