package duke.ui;

import duke.Duke;
import duke.exception.DukeException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    /**
     * Constructor for the Ui class, initiates a new Scanner instance.
     */
    public Ui() {
        dialogContainer = new VBox();
        this.showWelcome();
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

    /**
     * Starts the JavaFX GUI.
     * @param primaryStage default window
     */
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/Ui.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Duke");
            fxmlLoader.<Ui>getController().setDuke(duke);
            primaryStage.show();
            this.showWelcome();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints out welcome message.
     */
    private void showWelcome() {
        dialogContainer.getChildren().add(DialogBox.getUserDialog("Hello! I'm Duke\n"
                + "What can I do for you?", dukeImage));
    }

    /**
     * Method to handle input from the user.
     * Reads command and is passed to Duke class to be executed.
     */
    public void handleInput() {
        try {
            String exit = this.duke.execute(this.readCommand());
            this.printLine(exit);
            if ("Bye. Hope to see you again soon!".equals(exit)) {
                Platform.exit();
            }
        } catch (DukeException e) {
            this.printLine(e.toString());
        }
    }

    /**
     * Method to read command from the user.
     * @return String command that user inputted.
     */
    private String readCommand() {
        String command = commandTextField.getText();
        commandTextField.setText("");
        dialogContainer.getChildren().add(DialogBox.getUserDialog(command, userImage));
        return command;
    }

    /**
     * Prints line.
     *
     * @param line line to be printed.
     */
    private void printLine(String line) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(line, dukeImage));
    }

    /**
     * Print error when storage file is empty.
     */
    public void showLoadingError() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog("You have not stored any tasks!", dukeImage));
    }
}
