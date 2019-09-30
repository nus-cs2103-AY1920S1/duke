package duke.util.gui.messagebox;

import duke.util.gui.ColourScheme;
import duke.util.gui.MainWindow;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
// todo: replace Label with TextFlow to enable text to wrap multiple lines!
// todo: still can't get user input to align right :(
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

    public static MessageBox getUserMessageBox(String text, ColourScheme colourScheme) {
        MessageBox userMessageBox = new MessageBox(text);
        userMessageBox.setStyle("-fx-background-color: " + colourScheme.getUserMessageBoxColour());
        userMessageBox.message.setStyle("-fx-fill: "+ colourScheme.getTextColour());
        DropShadow dropShadow = new DropShadow(5.0, Color.web(colourScheme.getUserShadowColour()));
        userMessageBox.setEffect(dropShadow);
//        userMessageBox.setAlignment(Pos.TOP_RIGHT);
        return userMessageBox;
    }

    public static MessageBox getDukeMessageBox(String text, ColourScheme colourScheme) {
        MessageBox dukeMessageBox = new MessageBox(text);
        dukeMessageBox.setStyle("-fx-background-color: " + colourScheme.getDukeMessageBoxColour());
        dukeMessageBox.message.setStyle("-fx-fill: "+ colourScheme.getTextColour());
        DropShadow dropShadow = new DropShadow(5.0, Color.web(colourScheme.getDukeShadowColour()));
        dukeMessageBox.setEffect(dropShadow);
//        dukeMessageBox.setAlignment(Pos.TOP_LEFT);
        return dukeMessageBox;
    }

    // todo: getDukeExceptionMessageBox
    // todo: getDukeTaskMessageBox
}
