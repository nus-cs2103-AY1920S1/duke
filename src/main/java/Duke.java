import exception.DukeException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import java.text.ParseException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents a task manager.
 * A Duke object has a file with the list of tasks.
 * Tasks can be added, deleted and updated.
 *
 * @author Michelle Yong
 */
public class Duke {
    protected Storage storage;
    protected TaskList taskList;
    protected Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass()
            .getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass()
            .getResourceAsStream("/images/DaDuke.png"));

    /**
     * Creates the task manager with the relevant ui, storage and task list.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("/Users/michelleyong/Desktop/duke/data/duke.txt");
        try {
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException f) {
            System.out.println(f);
        } catch (ParseException p) {
            System.out.println(p);
        }
    }

    String getResponse(String input) throws IOException, ParseException {
        try {
            Command command = Parser.getCommand(input);
            return command.execute(storage, taskList, ui);
        } catch (DukeException e) {
            return ui.showUnknownCommandError();
        }
    }

    /**
     * Gets the user interface used.
     *
     * @return The user interface.
     */
    public Ui getUi() {
        return this.ui;
    }
}