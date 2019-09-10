import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A Task Manager that allows users to record their tasks.
 */

public class Duke {

    private UI ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Construct a object to run the whole task manager.
     * @param filePath Directory of the file for saving and loading
     */
    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        //load previous task list. If not found, create a new one
        try {
            tasks = new TaskList(storage.load());
            ui.showLoadingSuccessful();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String reply;
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);

            assert c.toString() != "" : "Response from Duke cannot be empty!";
            return c.toString();
        } catch (DukeException e) {
            reply = e.toString();
        }
        return reply;
    }
}
