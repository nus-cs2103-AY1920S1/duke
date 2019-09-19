package SerSnapsalot.duke;

import SerSnapsalot.command.Command;
import SerSnapsalot.exception.DukeException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import SerSnapsalot.parser.Parser;
import SerSnapsalot.storage.Storage;
import SerSnapsalot.taskList.TaskList;
import SerSnapsalot.ui.Ui;
import SerSnapsalot.GUI.DialogBox;


public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke() {
        ui = new Ui();
        ui.showWelcome();
        this.storage = new Storage();
        try {
            taskList = storage.displayTaskList();
        } catch (DukeException e) {
            System.out.println(e);
            taskList = new TaskList();
        }
        parser = new Parser();
    }

    /**
     * Receives input from the GUI as user input and sends it to be parsed.
     * Places the user input and response in a DialogBoxes and display them.
     * @param stage The stage of the GUI.
     */
    private void handleUserInput(Stage stage) {
        String userText = (userInput.getText());
        String dukeText = (getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * Sends user input to be parsed into a command.Command, which is then executed.
     * Returns message to be placed in Dialog Box for Duke's response.
     * @param fullCommand The String from user input.
     * @return output The output message after parsing the command through Duke.
     */
    public String getResponse(String fullCommand) {
        String output = "";
        try {
            Command command = parser.parse(fullCommand);
            output = command.execute(ui, taskList, storage);
        } catch (DukeException e) {
            output = e.toString();
        }
        return output;
    }

    public static void main(String[] args) {

    }
}
