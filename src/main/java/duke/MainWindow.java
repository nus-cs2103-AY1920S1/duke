package duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        this.duke = d;
        this.duke.initialize(this);
    }

    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        this.addUserDialog(input);
        this.userInput.clear();

        boolean isExit = this.duke.getResponse(input);
        if (isExit) {
            Platform.exit();
        }
    }

    public void addDukeDialog(String message) {
        this.dialogContainer.getChildren().add(DialogBox.getDukeDialog(message, this.dukeImage));
    }

    public void addUserDialog(String message) {
        this.dialogContainer.getChildren().add(DialogBox.getUserDialog(message, this.userImage));
    }

}