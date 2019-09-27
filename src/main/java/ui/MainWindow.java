package ui;

import command.HelloCommand;
import driver.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;



/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * AnchorPane holds all other features:
 * -->Scrollpane, which is a Vbox containing each Label (text) and ImageView(Picture)
 * -->Textfield, where the user inputs tect
 * --> Button to press enter
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void sayHello() {
        HelloCommand hi = new HelloCommand();
        String response = hi.executeCommand();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage));
    }


    public void setDuke(Duke d) {
        duke = d;
        sayHello();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage));
        String response = duke.getResponse(input);
       // dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.getChildren().addAll(
        DialogBox.getDukeDialog(response, dukeImage));

        userInput.clear();
    }
}