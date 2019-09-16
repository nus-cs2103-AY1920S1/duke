import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
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
        this.storage = new Storage("duke.txt");
        try {
            taskList = storage.displayTaskList();
        } catch (DukeException e) {
            System.out.println(e);
            taskList = new TaskList();
        }
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String fullCommand) {
        String output = "";
        try {
            Command command = Parser.parse(fullCommand);
            output = command.execute(ui, taskList, storage);
        } catch (DukeException e) {
            output = e.toString();
        }
        return output;
    }

	public void run() {
        String fullCommand = ui.readCommand();
        boolean isExit = false;
        while (!isExit) {
            try {
                Command command = Parser.parse(fullCommand);
                command.execute(ui, taskList, storage);

            } catch (DukeException e) {
                System.out.println (e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
