package duke;

import duke.dukeexception.DukeException;
import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Response;
import duke.ui.Ui;


/**
 * Class that serves as the main driver for the Duke application.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

//    /** JavaFX variables. */
//    private ScrollPane scrollPane;
//    private VBox dialogContainer;
//    private TextField userInput;
//    private Button sendButton;
//    private Scene scene;
//    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
//    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Class constructor that specifies file path to load storage from.
     *
     * @param filepath File path to load storage from.
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
    }

    public Duke(){
        this.ui = new Ui();
        this.storage = new Storage("data/duke.txt");
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.getLoadingErrorResponse();
            this.taskList = new TaskList();
        }
    }

    private Response process(String input) {
        Response response;
        try {
            Command c = Parser.parse(input);
            response = c.execute(taskList, ui, storage);
        } catch (DukeException de) {
            response = ui.getErrorResponse(de.getMessage());
        }

        return response;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public Response getResponse(String input) {
        Response response = process(input);
        return response;
    }
}
