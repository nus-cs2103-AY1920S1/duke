package duke.controllers;

import duke.main.DialogBox;
import duke.main.Duke;
import duke.ui.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/hehe.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/theMan.jpg"));

    @FXML
    public void initialize() {
        Ui ui = new Ui();

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String style = "-fx-background-color: rgb(102, 0, 26);";
        this.dialogContainer.setStyle(style);

        //duke welcome message upon opening GUI
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(ui.showWelcomeMessage(), dukeImage)
        );
    }

    public void setDuke(Duke d) {
        assert d!=null : "Instance of duke should be valid";
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing duke.main.Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        assert input!=null : "input should be valid";
        String response = duke.getResponse(input);
        assert response!=null : "response should be valid";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}

