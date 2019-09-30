package duke;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.dukeexception.DukeException;
import duke.command.Command;
import duke.parser.Parser;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

/**
 * duke.view.Main driver class for Duke application. Duke application helps the user to manage tasks and allows the user to
 * add, remove, mark a task as completed, list all tasks and find all tasks containing a keyword.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private static final String SAVE_DIRECTORY =
            "C:/Users/gbrls/OneDrive/Desktop/duke-master/src/main/java/duke/data/tasks.txt";

    /**
     * Loads a save file and generates a new Ui, Storage and TaskList object stored as instance variables.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(SAVE_DIRECTORY);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    
    public String getResponse(String input) { //find a way to show welcome
        try {
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage); //refactor way to display ui message
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return "Duke heard: " + ui.getOutput();
    }
}