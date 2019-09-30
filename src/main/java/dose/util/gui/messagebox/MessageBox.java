package dose.util.gui.messagebox;

import dose.util.gui.MainWindow;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
// todo: replace Label with TextFlow to enable text to wrap multiple lines!
// todo: still can't get user input to align right :(
public abstract class MessageBox extends VBox {

    @FXML
    private Label message;

    @FXML
    HBox messageBox;

    MessageBox(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(
                "/view/MessageBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // I give up :(
        //messageBox.setAlignment(Pos.BOTTOM_LEFT);
        message.setText(text);
    }

    public Label getMessage() {
        return message;
    }

    public void setMessage(Label message) {
        this.message = message;
    }
}
