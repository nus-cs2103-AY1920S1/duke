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
        dialogContainer.getChildren().add(DialogBox.getUserDialog("Hello! I'm Duke\n" + "What can I do for you?", dukeImage));
    }

    public void handleInput() {
        try {
            String exit = this.duke.execute(this.readCommand());
            this.printLine(exit);
            if (exit.equals("Bye. Hope to see you again soon!")) {
                Platform.exit();
            }
        } catch (DukeException e) {
            this.printLine(e.toString());
        }
    }

    public String readCommand() {
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
    public void printLine(String line) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(line, dukeImage));
    }

    /**
     * Prints out error.
     *
     * @param err to be printed.
     */
    public void showError(String err) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(err, dukeImage));
    }

    /**
     * Print error when storage file is empty.
     */
    public void showLoadingError() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog("You have not stored any tasks!", dukeImage));
    }
}
