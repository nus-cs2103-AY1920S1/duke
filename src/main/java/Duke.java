import duke.parser.GuiParser;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.GuiTaskList;
import duke.tasklist.TaskList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

public class Duke  {

    
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Storage storage;
    private TaskList tasks;
    private GuiParser parser;
    ArrayList<Task> tempStore = new ArrayList<Task>();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructor for Duke instance.
     * @param filepath file path giving the location of the file
     */
    public Duke(String filepath) {
        storage = new Storage(filepath);
        //initialise an arraylist to store Tasks
        GuiTaskList store = new GuiTaskList(storage.createTasksFromFile(), storage);
        this.parser = new GuiParser(store);
    }


    /**
     * Generate a response to user input.
     * Calls Parser object to read current input from GUI
     */
    private String getResponse(String input) {
        return parser.readUserInput(input);
    }


    /**
     * accessor to getResponse() method
     * @param input
     * @return
     */
    public String getPublicResponse(String input) {
        return this.getResponse(input);
    }





}
