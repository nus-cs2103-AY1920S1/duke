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
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, String backgroundColour, String textColour) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        dialog.setMinSize(Label.USE_COMPUTED_SIZE, Label.USE_PREF_SIZE);
        displayPicture.setImage(img);
        dialog.setStyle("-fx-background-color:" + backgroundColour + "; -fx-background-radius: 15; "
                + "-fx-text-fill:" + textColour);
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

    /**
     * Creates the text bubble for User.
     *
     * @param text User input.
     * @param img User image.
     * @return Text bubble of user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, "#4996f5", "#ffffff");
    }

    /**
     * Creates the text bubble for Chick.
     *
     * @param text System response.
     * @param img Chick image.
     * @return Text bubble of chick.
     */
    public static DialogBox getTaskChickDialog(String text, Image img) {
        var db = new DialogBox(text, img, "#e5e5e9", "#000000");
        db.flip();
        return db;
    }
}