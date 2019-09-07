package duke.view;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends BorderPane {
    @FXML
    private HBox logoBox;
    @FXML
    private ImageView logoPicture;
    @FXML
    private HBox dialogBox;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox scrollBox;
    @FXML
    private HBox inputBox;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image logoImage = new Image(this.getClass().getResourceAsStream("/images/logo.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(scrollBox.heightProperty());
        scrollPane.setContent(scrollBox);
        scrollBox.setId("scrollBox");
        logoBox.setId("logoBox");
        logoPicture.setImage(logoImage);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        scrollBox.getChildren().addAll(
            DialogBox.getUserDialog(input),
            DialogBox.getDukeDialog(input)
            );
        userInput.clear();
        if (input.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished( event -> {Platform.exit(); System.exit(0 );});
            delay.play();
        }
    }

}
