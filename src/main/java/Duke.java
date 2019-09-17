import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Duke {


    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    static String SUCCESS = "Setup Successful";

    /**
     * Constructor.
     *
     * @param filePath Location of save file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    /**
     * Tries to load save file.
     *
     * @return string message signifying success or failure
     */
    public String setUp() {
        try {
            tasks = new TaskList(storage.load());
            return SUCCESS;
        } catch (DukeException e) {
            tasks = new TaskList();
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Returns welcome message.
     *
     * @return Welcome string
     */
    public String welcome() {
        return ui.showWelcome();
    }

    /**
     * Runs input through parser and execute command.
     *
     * @return result of executing input
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Command c = Parser.parse(input);
            response = c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }

        return response;

    }

}
