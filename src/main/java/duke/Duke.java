package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeIoException;
import duke.gui.DialogBox;
import duke.storage.Storage;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Main Driver class housing the infinite loop.
 */
public class Duke {
    private final Image IMAGE_USER = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image IMAGE_DUKE = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    // JavaFX
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = storage.readFromDisk(); // leave index 0 empty for clarity
        } catch (DukeIoException e) {
            System.err.println(ui.showError(e));
            this.taskList = new TaskList(); // only load the taskList if no error
        }
    }

    // JavaFX GUI won't run without this.
    public Duke() {
        this("data/duke.txt");
    }

    /**
     * The main loop for Duke.
     */
    public CommandResponse getResponse(String fullCommand) {
        Command c = Parser.parse(fullCommand);
        return c.execute(taskList, ui, storage);
    }

//    public void run() {
//        ui.showWelcomeMessage();
//        boolean isExit = false;
//
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                ui.showLine(); // show the divider line
//                Command c = Parser.parse(fullCommand);
//                c.execute(taskList, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showError(e);
//            } finally {
//                ui.showLine();
//            }
//        }
//    }

//    public static void main(String[] args) {
//        new Duke("data/duke.txt").run();
//    }

//    public String getResponse(String input) {
//        return "Duke heard: " + input;
//    }
}
