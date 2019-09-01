package duke.ui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Ui class that handles all IO for the Duke application.
 */
public class Ui {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    private CommandBox commandBox;
    private Duke duke;

    /**
     * Constructor for the Ui class, initiates a new Scanner instance.
     */
    public Ui() {
        commandBox = new CommandBox();
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
//        resultDisplay.setFeedbackToUser("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public String readCommand() {
        return commandBox.handleCommandEntered();
    }

    /**
     * Prints line.
     *
     * @param line line to be printed.
     */
    public void printLine(String line) {
//        resultDisplay.setFeedbackToUser(line);
    }

    /**
     * Prints out error.
     *
     * @param err to be printed.
     */
    public void showError(String err) {
//        resultDisplay.setFeedbackToUser(err);
    }

    /**
     * Print error when storage file is empty.
     */
    public void showLoadingError() {
//        resultDisplay.setFeedbackToUser("You have not stored any tasks!");
    }
}
