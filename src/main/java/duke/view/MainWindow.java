package duke.view;

import duke.Duke;
import duke.util.Ui;

import duke.view.DialogBox;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * JavaFX Controller for MainWindow. Provides the layout for the MainWindow,
 * and defines the behaviour for on-screen GUI elements via fxml.
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
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.HELLO, dukeImage));
    }

    public void setDuke(Duke d) {
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
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        userInput.clear();
        publishDukeResponse(response);
    }

    // given a string with multiple lines (identified by terminating newlines),
    // returns an ArrayList object with each entry
    // containing a maximum of n lines from the original string.
    private ArrayList<String> splitEveryNthLine(String response, int n) {
        String[] splitStr = response.split("\n");
        StringBuilder sb = new StringBuilder();
        ArrayList<String> returnList = new ArrayList<>();

        for (int i = 0; i < splitStr.length; i++) {
            sb.append(splitStr[i]);
            sb.append("\n");
            if ((i + 1) % n == 0 || i + 1 == splitStr.length) {
                returnList.add(sb.toString());
                sb.setLength(0);
            }
        }
        return returnList;
    }

    // publishes Duke response, with each box containing maximum of 7 lines to
    // prevent text overrun/truncation.
    private void publishDukeResponse(String response) {
        ArrayList<String> splitResponse = splitEveryNthLine(response, 7);

        for (int i = 0; i < splitResponse.size(); i++) {
            // if first box, display duke image. else no image.
            DialogBox box = DialogBox.getDukeDialog(splitResponse.get(i), (i == 0) ? dukeImage : null);
            box.setPadding(new Insets((i == 0) ? 15 : 0, // if first box, use normal top padding. else 0 top padding
                    5, // normal right padding
                    (i + 1 == splitResponse.size()) ? 15 : 0, // if last box, use normal padding. else 0 padding.
                    5)); // normal left padding
            dialogContainer.getChildren().add(box);
        }
    }
}