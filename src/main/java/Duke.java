import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;
import java.io.FileNotFoundException;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;

/**
 * The main class of the program.
 */
public class Duke {

    /**
     * To indicate whether the program is done.
     */
    private boolean exitProgram = false;

    /**
     * The storage containing the file to be modified in the hard drive.
     */
    private Storage storage;

    /**
     * The ui to print out the messages.
     */
    private UI ui;

    /**
     * The list of tasks.
     */
    private TaskList taskList;

    /**
     * The scrolling pane of the application.
     */
    private ScrollPane scrollPane;

    /**
     * The dialog window of the application.
     */
    private VBox dialogContainer;

    /**
     * The box for the user input;
     */
    private TextField userInput;

    /**
     * The send button for user input.
     */
    private Button sendButton;

    /**
     * The scene of the application.
     */
    private Scene scene;

    /**
     * The profile image for the user.
     */
    private Image user = new Image(this.getClass().getResourceAsStream("/images/Wario.jpg"));

    /**
     * The profile image for duke.
     */
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Waluigi.jpg"));

    /**
     * Constructor that creates the main Duke class.
     * @throws Exception Used for when there are any errors.
     */
    public Duke() throws Exception {
        String filepath = "src/main/Data/Duke.txt";
        this.storage = new Storage(filepath);
        storage.loadTasks();
        try {
            this.taskList = new TaskList(storage);
        } catch (FileNotFoundException error) {
            this.taskList = new TaskList();
        }
        this.ui = new UI(taskList);
    }

    /**
     * To exit the program;
     */
    public void terminate() {
        this.exitProgram = true;
    }

    public String getResponse(String input) throws Exception {
        if (exitProgram) {
            return "You said you are leaving so please leave >:(";
        }
        Command inputCommand = Parser.parse(input);
        inputCommand.execute(taskList,storage,ui);
        return inputCommand.toString();
    }
}
