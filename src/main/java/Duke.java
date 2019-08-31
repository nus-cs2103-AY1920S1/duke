import duke.UI.Ui;
import duke.parser.GuiParser;
import duke.parser.Parser;
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
    private Ui ui;
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
     * Driver method for Duke instance.
     * calls parser to parse user input accordingly
     */

   /* public void run() {
        parser.readUserInput();
    }*/

    /**
     * Entry point of program, instantiates Duke instance.
     * file input/output is loaded from data/duke.txt
     * @return void
     */
    /*public static void main(String[] args) {
        Duke temp = new Duke("data/duke.txt");
        Ui.showGreeting();
        temp.run();
    }*/

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return parser.readUserInput(input);
    }

    public String getPublicResponse(String input) {
        return this.getResponse(input);
    }





}
