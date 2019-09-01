package duke.ui;

import duke.Duke;
import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Ui class that handles all IO for the Duke application.
 */
public class Ui extends AnchorPane {
    public TextField commandTextField;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    private Duke duke;

    /**
     * Constructor for the Ui class, initiates a new Scanner instance.
     */
    public Ui() {
        dialogContainer = new VBox();
        scrollPane = new ScrollPane();
        commandTextField = new TextField();
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/Ui.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            primaryStage.setScene(scene);
            fxmlLoader.<Ui>getController().setDuke(duke);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints out welcome message.
     */
    public void showWelcome() {
        dialogContainer.getChildren().add(DialogBox.getUserDialog("Hello! I'm Duke\n" + "What can I do for you?"));
    }

    public void handleInput()  {
        try {
            boolean exit = this.duke.execute(this.readCommand());
            if (exit) {
                Platform.exit();
            }
        } catch (DukeException e) {
            this.printLine(e.toString());
        }
    }

    public String readCommand() {
        String command = commandTextField.getText();
        commandTextField.setText("");
        dialogContainer.getChildren().add(DialogBox.getUserDialog(command));
        return command;
    }

    /**
     * Prints line.
     *
     * @param line line to be printed.
     */
    public void printLine(String line) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(line));
    }

    /**
     * Prints out error.
     *
     * @param err to be printed.
     */
    public void showError(String err) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(err));
    }

    /**
     * Print error when storage file is empty.
     */
    public void showLoadingError() {
        dialogContainer.getChildren().add(DialogBox.getUserDialog("You have not stored any tasks!"));
    }
}
