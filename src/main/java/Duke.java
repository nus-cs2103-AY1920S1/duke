import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * The main class of the program.
 */
public class Duke {

    /**
     * This specifies the filepath for use.
     */
    public static final String filepath = "Data/Tasks.txt";

    /**
     * To indicate whether the program is done.
     */
    public boolean exitProgram = false;

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
     * The box for the user input.
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
     * Constructor that creates the main Duke class.
     * @throws Exception Used for when there are any errors.
     */
    public Duke() throws Exception {
        InputStream in = this.getClass().getResourceAsStream(filepath);
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
     * To exit the program.
     */
    public void terminate() {
        this.exitProgram = true;
    }

    /**
     * This method produces a response from Radomir to be displayed.
     * @param input The user's input.
     * @return Radomir's response in string form.
     * @throws Exception Handles exceptions that occurs when the method runs.
     */
    public String getResponse(String input) throws Exception {
        if (exitProgram) {
            return "You said you are leaving so please leave >:(";
        }
        Command inputCommand = Parser.parse(input);
        inputCommand.execute(taskList,storage,ui);
        return inputCommand.toString();
    }
}
