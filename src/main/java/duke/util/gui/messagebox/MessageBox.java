package duke.util.gui.messagebox;

import duke.util.gui.ColourScheme;
import duke.util.gui.MainWindow;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class MessageBox extends HBox {
    @FXML
    private Label message;

    private MessageBox(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(
                "/view/MessageBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        message.setText(text);
    }

//    /**
//     * Flips the dialog box such that the ImageView is on the left and text on the right.
//     */
//    private void flip() {
//        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
//        Collections.reverse(tmp);
//        getChildren().setAll(tmp);
//        setAlignment(Pos.TOP_LEFT);
//    }

    public static MessageBox getUserDialog(String text, ColourScheme colourScheme) {
        MessageBox userDialogBox = new MessageBox(text);
        userDialogBox.setStyle("-fx-background-color: " + colourScheme.getUserLightColour());
        userDialogBox.message.setStyle("-fx-fill: "+ colourScheme.getTextColour());
        return userDialogBox;
    }

    public static MessageBox getDukeDialog(String text, ColourScheme colourScheme) {
        MessageBox dukeDialogBox = new MessageBox(text);
        dukeDialogBox.setStyle("-fx-background-color: " + colourScheme.getDukeMediumColour());
        dukeDialogBox.message.setStyle("-fx-fill: "+ colourScheme.getTextColour());
        return dukeDialogBox;
    }
}
