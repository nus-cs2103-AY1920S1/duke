import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle myCircle;
    private static Image userImage = new Image(DialogBox.class.getResourceAsStream("/images/DaUser.png"));
    private static Image dukeImage = new Image(DialogBox.class.getResourceAsStream("/images/DaIceBear.png"));

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        fillCircle(img);

    }

    private void fillCircle(Image img) {
        myCircle.setStroke(Color.valueOf("#0e18dd"));
        myCircle.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text) {
        return new DialogBox(text, userImage);
    }

    /**
     * Obtains the Dialog Box to display the message from Duke to the User.
     * @param text The message to be displayed.
     * @return A Dialog Box that shows Duke talking to the user.
     */
    public static DialogBox getDukeDialog(String text) {
        DialogBox db = new DialogBox(text, dukeImage);
        db.flip();
        return db;
    }
}