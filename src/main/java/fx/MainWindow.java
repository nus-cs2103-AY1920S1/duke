package fx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private DukeFx duke;
    private Image userImage;
    private Image dukeImage;

    public MainWindow() {
        File userFile = new File("src/main/assets/DaUser.png");
        File dukeFile = new File("src/main/assets/DaDuke.png");

        try {
            userImage = new Image(new FileInputStream(userFile));
            dukeImage = new Image(new FileInputStream(dukeFile));
        } catch (
                FileNotFoundException e) {
            System.out.println("Image files not found");
            return;
        }
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(DukeFx d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                FxDialogBox.getUserDialog(input, userImage),
                FxDialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
