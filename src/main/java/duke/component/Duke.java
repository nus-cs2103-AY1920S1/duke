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

    //Create an initialise two user image for chatbot
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

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
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return (e.getMessage());
        }

    }


}
