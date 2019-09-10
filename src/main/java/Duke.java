import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import command.Command;
import main.DukeException;
import main.Parser;
import main.Storage;
import main.TaskList;
import main.Ui;


/**
 * Main class.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new Duke object
     */
    public Duke() {
        String filePath = "/Users/zhangxuan/Desktop/CS2103/duke/data/tasks.txt";
        // Currently hardcoded.
        ui = new Ui();
        storage = new Storage(filePath);
        if (!storage.isValidFilePath()) {
            try {
                storage = new Storage();
            } catch (DukeException e) {
                ui.dukeEcho((e.getMessage()));
            }
        }
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.dukeEcho(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Returns the message to be displayed to the user in String format after parsing the
     * user's full command.
     *
     * @param fullCommand The full command that the user input.
     * @return Duke's response to the full command in String format.
     */
    public String run(String fullCommand) {

        String response = "";
        //response += Ui.DIVIDER + "\n";
        try {
            Command c = Parser.parse(fullCommand);
            response += c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            response += ui.getErrorMessage(e.getMessage());
        }
        //response += Ui.DIVIDER + "\n";
        return response;
    }
    /**
     * Note to self:
     * At this moment in the application the text cuts off at 4 lines. Figure out how to make it dynamic
     */

}