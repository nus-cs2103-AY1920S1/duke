package duke;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import java.io.FileNotFoundException;

/**
 * The Duke program implements an application that
 * acts like a digital notebook for the user.
 *
 * @author Calvin
 * @version 1.0
 * @since 2019-08-20
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
    // private Image dukebot = new Image(this.getClass().getResourceAsStream("/pikachu.png"));
    //private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/pikachu.png"));

    public Duke() {}


    /**
     * This constructor takes in the filePath and initiates the necessary
     * classes required.
     *
     * @param filePath the local directory of the file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            //ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    /*
    public void run() {
        ArrayList<Task> list = tasks.getList();
        //ui.showWelcome();
        boolean isExit = false;
    }
    */

    public static void main(String[] args) {
        //new Duke("C:\\duke\\src\\main\\java\\data\\duke.txt").run();
    }

    String getResponse(String input) {
        //duke.TaskList taskList = new duke.TaskList(storage.load());;
        Duke duke = new Duke("C:\\duke\\src\\main\\java\\data\\duke.txt");
        return Parser.parse(input).execute(duke.tasks, duke.ui, duke.storage);
    }
}

