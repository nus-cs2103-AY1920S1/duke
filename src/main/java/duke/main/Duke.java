package duke.main;

import duke.component.Ui;
import duke.component.TaskList;
import duke.database.Storage;
import duke.command.Command;
import duke.component.Parser;
import duke.exception.DukeException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

/**
 * Duke class handle the initialise of the Duke Program,
 * database and exit of the program.
 *
 * @author TeoShyanJie
 *
 */
public class Duke {
    /** Database of the Duke Program. */
    private Storage storage;

    /** List of task. */
    private TaskList tasks;

    /** Ui of the Duke Program. */
    private Ui ui;

    /** Panel of Duke Program. */
    private ScrollPane scrollPane;

    /** Dialog Box of user and Duke. */
    private VBox dialogContainer;

    /** User Input. */
    private TextField userInput;

    /** Send Button. */
    private Button sendButton;

    /** The Scene of Duke Program. */
    private Scene scene;

    /** Image of User. */
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));

    /** Image of Duke Program. */
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Duke program to initalise the Database, Ui and get the task list.
     */
    public void initialise() {
        ui = new Ui();
        storage = new Storage(ui);
        storage.initialise();
        tasks = new TaskList(storage.getSavedTask());
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String command = "";

        try {
            Command c = Parser.parse(input);
            command = c.execute(tasks, storage);
        } catch (DukeException ex) {
            command = ex.getMessage();
        }

        return command;
    }
}